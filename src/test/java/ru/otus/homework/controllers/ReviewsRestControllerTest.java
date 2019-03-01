package ru.otus.homework.controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework.models.Book;
import ru.otus.homework.models.Review;
import ru.otus.homework.services.DatabaseService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.homework.controllers.Constants.REST_API;
import static ru.otus.homework.controllers.Constants.REST_V1_REVIEWS;
import static ru.otus.outside.utils.TestData.createBook0;
import static ru.otus.outside.utils.TestData.createReview0;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewsRestController.class)
class ReviewsRestControllerTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    DatabaseService databaseService;

    @Test
    void getReviews() throws Exception
    {
        Book book0 = createBook0();
        Review review0WithBook = createReview0(book0);

        when(databaseService.getAllReviewsForBookById(1L))
            .thenReturn(Collections.singletonList(review0WithBook));
        mvc.perform(get(REST_API + REST_V1_REVIEWS + "/0"))
            .andExpect(status().isOk());
            // .andExpect(content().json(expectedJson));
    }

}