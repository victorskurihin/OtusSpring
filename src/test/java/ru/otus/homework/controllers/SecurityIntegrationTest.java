package ru.otus.homework.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.otus.homework.dao.UserProfileDao;
import ru.otus.homework.models.UserProfile;
import ru.otus.homework.services.DatabaseService;
import ru.otus.homework.services.security.UserProfileDetailsService;

import javax.servlet.Filter;

import java.util.Optional;


import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static ru.otus.homework.controllers.Constants.*;
import static ru.otus.homework.security.Constants.FAILURE_URL;
import static ru.otus.homework.security.Constants.PARAMETER_PASSWORD;
import static ru.otus.homework.security.Constants.PARAMETER_USERNAME;

@ExtendWith(SpringExtension.class)
@WebMvcTest({
    AuthorsController.class, AuthorsRestController.class,
    BooksController.class, BooksRestController.class,
    ReviewsController.class, ReviewsRestController.class,
    LoginController.class
})
@DisplayName("Integration tests for controllers")
public class SecurityIntegrationTest
{
    public static final String TEST_USER = "user";

    public static final String TEST_USER_PASSWORD_PLAIN = "12345";

    public static final String TEST_USER_PASSWORD = "$2a$10$SUisyGKmRgMklxFohZeoKetBMqd7pPj45GJAD4.e5hD2BgWBgS9oa";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @MockBean
    private UserProfileDetailsService userProfileDetailsService;

    private MockMvc mvc;

    @MockBean
    private DatabaseService databaseService;

    @MockBean
    UserProfileDao userProfileDao;

    void mockMvcAndUserProfileDao()
    {
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .addFilters(springSecurityFilterChain)
            .apply(springSecurity())
            .build();
        when(userProfileDao.findByLogin(TEST_USER))
            .thenReturn(Optional.of(new UserProfile(1, TEST_USER, TEST_USER_PASSWORD, false, false)));
    }

    @BeforeEach
    void setUp()
    {
        mockMvcAndUserProfileDao();
    }

    @Test
    public void notAuthenticatedAccess() throws Exception
    {
        mvc.perform(get(REQUEST_BOOK_AUTHORS_LIST)).andExpect(status().isFound());
        mvc.perform(get(REQUEST_AUTHOR_CREATE).param("bookId", "1")).andExpect(status().isFound());
        mvc.perform(get(REQUEST_AUTHOR_EDIT).param("authorId","1").param("bookId", "1"))
            .andExpect(status().isFound());
        mvc.perform(get("/")).andExpect(status().isFound());
        mvc.perform(get(REQUEST_BOOK_CREATE)).andExpect(status().isFound());
        mvc.perform(get(REQUEST_BOOK_EDIT).param("bookId", "1")).andExpect(status().isFound());
        mvc.perform(get(REQUEST_REVIEWS_LIST)).andExpect(status().isFound());
        mvc.perform(get(REQUEST_REVIEW_CREATE).param("bookId", "1")).andExpect(status().isFound());
        mvc.perform(get(REQUEST_REVIEW_EDIT).param("reviewId","1").param("bookId", "1"))
            .andExpect(status().isFound());

        mvc.perform(get(REST_API + REST_V1_AUTHORS + "/0")).andExpect(status().isFound());
        mvc.perform(post(REST_API + REST_V1_AUTHORS)).andExpect(status().isFound());
        mvc.perform(put(REST_API + REST_V1_AUTHORS)).andExpect(status().isFound());
        mvc.perform(delete(REST_API + REST_V1_AUTHORS + "/0")).andExpect(status().isFound());

        mvc.perform(get(REST_API + REST_V1_BOOKS)).andExpect(status().isFound());
        mvc.perform(post(REST_API + REST_V1_BOOKS)).andExpect(status().isFound());
        mvc.perform(put(REST_API + REST_V1_BOOKS)).andExpect(status().isFound());
        mvc.perform(delete(REST_API + REST_V1_BOOKS + "/0")).andExpect(status().isFound());

        mvc.perform(get(REST_API + REST_V1_REVIEWS + "/0")).andExpect(status().isFound());
        mvc.perform(post(REST_API + REST_V1_REVIEWS)).andExpect(status().isFound());
        mvc.perform(put(REST_API + REST_V1_REVIEWS)).andExpect(status().isFound());
        mvc.perform(delete(REST_API + REST_V1_REVIEWS + "/0")).andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    public void authenticatedAccess() throws Exception
    {
        mvc.perform(get(REQUEST_AUTHOR_CREATE).param("bookId", "1")).andExpect(status().isOk());
        mvc.perform(get(REQUEST_AUTHOR_EDIT).param("authorId","1").param("bookId", "1"))
            .andExpect(status().isOk());
        mvc.perform(get("/")).andExpect(status().isOk());
        mvc.perform(get(REQUEST_BOOK_CREATE)).andExpect(status().isOk());
        mvc.perform(get(REQUEST_REVIEW_CREATE).param("bookId", "1")).andExpect(status().isOk());
        mvc.perform(get(REST_API + REST_V1_AUTHORS + "/0")).andExpect(status().isOk());
        mvc.perform(get(REST_API + REST_V1_BOOKS)).andExpect(status().isOk());
        mvc.perform(get(REST_API + REST_V1_REVIEWS + "/0")).andExpect(status().isOk());
    }

    @Test
    public void testLogin() throws Exception
    {
        mvc.perform(formLogin(REQUEST_LOGIN_PROCESS)
            .userParameter(PARAMETER_USERNAME)
            .passwordParam(PARAMETER_PASSWORD)
            .user(TEST_USER)
            .password(TEST_USER_PASSWORD_PLAIN + '_')
        ).andExpect(redirectedUrl(FAILURE_URL));

        mvc.perform(formLogin(REQUEST_LOGIN_PROCESS)
            .user(PARAMETER_USERNAME,"user")
            .password(PARAMETER_PASSWORD,"12345")
        ).andExpect(redirectedUrl("/"));
    }
}
