package ru.otus.homework.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import ru.otus.homework.dao.LibraryUsersDao;
import ru.otus.homework.models.LibraryUser;
import ru.otus.homework.services.DatabaseService;


import javax.servlet.Filter;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.homework.TestConst.*;
import static ru.otus.homework.controllers.Constants.REQUEST_LOGIN_PROCESSING;
import static ru.otus.homework.security.SecurityConst.FAILURE_URL;
import static ru.otus.homework.security.SecurityConst.PASSWORD_PARAMETER;
import static ru.otus.homework.security.SecurityConst.USERNAME_PARAMETER;

@ExtendWith(SpringExtension.class)
@WebMvcTest({
    AuthorsController.class, AuthorsRestController.class,
    BooksController.class, BooksRestController.class,
    ReviewsController.class, ReviewsRestController.class,
    LoginController.class
})
public class SecurityIntegrationTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @MockBean
    LibraryUsersDao libraryUsersDao;

    @MockBean
    DatabaseService databaseService;

    MockMvc mvc;

    @BeforeEach
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .addFilters(springSecurityFilterChain)
            .apply(springSecurity()).build();
        when(libraryUsersDao.findByLogin(TEST_LIBRARY_USER))
            .thenReturn(new LibraryUser(TEST_ID_LONG, TEST_LIBRARY_USER, TEST_LIBRARY_USER_PASSWORD_ENCODED));
    }

    @Test
    public void testLogin() throws Exception {
        mvc.perform(formLogin(REQUEST_LOGIN_PROCESSING)
            .userParameter(TEST_AUTHOR_NAME)
            .passwordParam(PASSWORD_PARAMETER)
            .user(TEST_LIBRARY_USER)
            .password(TEST_LIBRARY_USER_PASSWORD_PLAIN)
        ).andExpect(redirectedUrl(FAILURE_URL));

        mvc.perform(formLogin(REQUEST_LOGIN_PROCESSING)
            .userParameter(USERNAME_PARAMETER)
            .passwordParam(PASSWORD_PARAMETER)
            .user(TEST_LIBRARY_USER)
            .password(TEST_LIBRARY_USER_PASSWORD_PLAIN)
        ).andExpect(redirectedUrl("/"));
    }
}
