package ru.otus.homework.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import ru.otus.homework.models.LibraryUser;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.homework.TestConst.*;
import static ru.otus.homework.controllers.MvcConsts.*;
import static ru.otus.homework.security.SecurityConst.*;

@WebMvcTest({BookController.class, BookRestController.class, BookCommentRestController.class})
public class SecurityTest extends AbstractControllerTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        when(libraryUsersDao.findByLogin(TEST_LIBRARY_USER)).thenReturn(new LibraryUser(TEST_ID_LONG, TEST_LIBRARY_USER, TEST_LIBRARY_USER_PASSWORD_ENCODED));
    }

    @Test
    public void testNotAuthenticatedAccess() throws Exception{
        mvc.perform(get("/")).andExpect(status().isFound());
        mvc.perform(get(REQUEST_BOOK_DETAILS).param(PARAM_ID, TEST_ID_STRING)).andExpect(status().isFound());

        mvc.perform(get(REST_MAPPING_BOOK)).andExpect(status().isFound());
        mvc.perform(post(REST_MAPPING_BOOK)).andExpect(status().isFound());
        mvc.perform(put(REST_MAPPING_BOOK + TEST_ID_STRING)).andExpect(status().isFound());
        mvc.perform(delete(REST_MAPPING_BOOK + TEST_ID_STRING)).andExpect(status().isFound());

        mvc.perform(get(REST_MAPPING_COMMENT).param(PARAM_BOOK_ID, TEST_ID_STRING)).andExpect(status().isFound());
        mvc.perform(post(REST_MAPPING_COMMENT).param(PARAM_BOOK_ID, TEST_ID_STRING)).andExpect(status().isFound());
        mvc.perform(put(REST_MAPPING_COMMENT + TEST_ID_STRING).param(PARAM_BOOK_ID, TEST_ID_STRING)).andExpect(status().isFound());
        mvc.perform(delete(REST_MAPPING_COMMENT + TEST_ID_STRING).param(PARAM_BOOK_ID, TEST_ID_STRING)).andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    public void testAuthenticatedAccess() throws Exception{
        mvc.perform(get("/")).andExpect(status().isOk());
        mvc.perform(get(REQUEST_BOOK_DETAILS).param(PARAM_ID, TEST_ID_STRING)).andExpect(status().isOk());

        mvc.perform(get(REST_MAPPING_BOOK)).andExpect(status().isOk());
        mvc.perform(post(REST_MAPPING_BOOK)).andExpect(status().isOk());
        mvc.perform(put(REST_MAPPING_BOOK + TEST_ID_STRING)).andExpect(status().isOk());
        mvc.perform(delete(REST_MAPPING_BOOK + TEST_ID_STRING)).andExpect(status().isOk());

        mvc.perform(get(REST_MAPPING_COMMENT).param(PARAM_BOOK_ID, TEST_ID_STRING)).andExpect(status().isOk());
        mvc.perform(post(REST_MAPPING_COMMENT).param(PARAM_BOOK_ID, TEST_ID_STRING)).andExpect(status().isOk());
        mvc.perform(put(REST_MAPPING_COMMENT + TEST_ID_STRING).param(PARAM_BOOK_ID, TEST_ID_STRING)).andExpect(status().isOk());
        mvc.perform(delete(REST_MAPPING_COMMENT + TEST_ID_STRING).param(PARAM_BOOK_ID, TEST_ID_STRING)).andExpect(status().isOk());
    }

    @Test
    public void testLogin() throws Exception {
        mvc.perform(formLogin(REQUEST_LOGIN_PROCESSING).userParameter(TEST_AUTHOR_NAME).passwordParam(PASSWORD_PARAMETER)
                .user(TEST_LIBRARY_USER).password(TEST_LIBRARY_USER_PASSWORD_PLAIN)
        ).andExpect(redirectedUrl(FAILURE_URL));

        mvc.perform(formLogin(REQUEST_LOGIN_PROCESSING).userParameter(USERNAME_PARAMETER).passwordParam(PASSWORD_PARAMETER)
                .user(TEST_LIBRARY_USER).password(TEST_LIBRARY_USER_PASSWORD_PLAIN)
        ).andExpect(redirectedUrl("/"));
    }
}
