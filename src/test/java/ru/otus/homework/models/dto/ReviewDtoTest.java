package ru.otus.homework.models.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.otus.homework.models.Book;
import ru.otus.homework.models.Review;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.outside.utils.TestData.*;

@DisplayName("Class ReviewDto")
class ReviewDtoTest
{
    private ReviewDto review;

    @Test
    @DisplayName("is instantiated with new ReviewDto()")
    void isInstantiatedWithNew() {
        new ReviewDto();
    }

    @Nested
    @DisplayName("when new with empty constructor")
    class WhenNew
    {
        @BeforeEach
        void createNew()
        {
            review = new ReviewDto();
        }

        @Test
        @DisplayName("default values in Review()")
        void defaults()
        {
            assertThat(review).hasFieldOrPropertyWithValue("id", null);
            assertThat(review).hasFieldOrPropertyWithValue("bookId", null);
            assertThat(review).hasFieldOrPropertyWithValue("review", null);
        }

        @Test
        @DisplayName("Setter and getter for review")
        void testGetSetFirstName()
        {
            review.setReview(TEST);
            assertThat(review).hasFieldOrPropertyWithValue("review", TEST);
            assertEquals(TEST, review.getReview());
        }
    }

    @Nested
    @DisplayName("when new with all args constructor")
    class WhenNewAllArgsConstructor
    {
        @BeforeEach
        void createNew()
        {
            review = new ReviewDto(TEST_SID, TEST_SID, TEST);
        }

        @Test
        @DisplayName("initialized values in Review()")
        void defaults()
        {
            assertThat(review).hasFieldOrPropertyWithValue("id", TEST_SID);
            assertThat(review).hasFieldOrPropertyWithValue("bookId", TEST_SID);
            assertThat(review).hasFieldOrPropertyWithValue("review", TEST);
        }

        @Test
        @DisplayName("Equals for class Review and hashCode")
        void testEquals()
        {
            assertNotEquals(new ReviewDto(), review);
            ReviewDto expected = new ReviewDto(TEST_SID, TEST_SID, TEST);
            assertEquals(expected.hashCode(), review.hashCode());
            assertEquals(expected, review);
        }

        @Test
        @DisplayName("The length of string from Review::toString is great than zero")
        void testToString()
        {
            assertTrue(review.toString().length() > 0);
        }
    }

    @Nested
    @DisplayName("converters")
    class Converters
    {
        private Book book0 = createBook0();
        @Test
        void constructFromReview()
        {
            Review data = new Review(TEST_ID, TEST, book0);
            ReviewDto expected = new ReviewDto(TEST_SID, TEST_SID0, TEST);
            review = new ReviewDto(data);
            assertEquals(expected, review);
        }

        @Test
        void createReview()
        {
            review = new ReviewDto(TEST_SID, TEST_SID0, TEST);
            Review expected = new Review(TEST_ID, TEST, book0);
            Review test = review.createReview(book0);
            assertEquals(expected, test);
        }

        @Test
        void constructFromNull()
        {
            //noinspection ConstantConditions
            assertThrows(AssertionError.class,() -> new ReviewDto(null));
        }

        @Test
        void updateFromNull()
        {
            review = new ReviewDto();
            assertThrows(AssertionError.class,() -> review.updateReview(null));
        }
    }
}