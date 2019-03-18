package ru.otus.homework.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import ru.otus.homework.models.Book;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.otus.homework.TestConst.*;
import static ru.otus.homework.controllers.MvcConsts.*;

@WebMvcTest(BookController.class)
@WithMockUser
public class BookControllerTest extends AbstractControllerTest {

    private Book testBook;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        testBook = new Book(null, TEST_BOOK_NAME, TEST_BOOK_DESC, TEST_BOOK_PUB_YEAR, null, null);
    }

    @Test
    public void viewBooksList() throws Exception {
        when(dataStorageService.getAllBooks()).thenReturn(Collections.singletonList(testBook));
        mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_BOOKS_LIST));
    }

    @Test
    public void viewBookDetails() throws Exception {
        when(dataStorageService.getBookById(1L)).thenReturn(Optional.of(testBook));
        mvc.perform(get(REQUEST_BOOK_DETAILS + "?id=1")).andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_BOOK_DETAILS))
                .andExpect(model().attributeExists(MODEL_ATTR_BOOK))
                .andExpect(content().string(containsString(TEST_BOOK_NAME)));
    }
}