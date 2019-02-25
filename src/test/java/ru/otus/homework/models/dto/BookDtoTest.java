package ru.otus.homework.models.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.otus.homework.models.Book;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.outside.utils.TestData.*;

@DisplayName("Class BookDto")
class BookDtoTest
{
    private BookDto book;

    @Test
    @DisplayName("is instantiated with new BookDto()")
    void isInstantiatedWithNew() {
        new BookDto();
    }

    /*
    @Nested
    @DisplayName("when new with empty constructor")
    class WhenNew
    {
        @BeforeEach
        void createNew()
        {
            book = new BookDto();
        }

        @Test
        @DisplayName("default values in Book()")
        void defaults()
        {
            assertThat(book).hasFieldOrPropertyWithValue("id", null);
            assertThat(book).hasFieldOrPropertyWithValue("book", null);
        }

        @Test
        @DisplayName("Setter and getter for book")
        void testGetSetFirstName()
        {
            book.setBook(TEST);
            assertThat(book).hasFieldOrPropertyWithValue("book", TEST);
            assertEquals(TEST, book.getBook());
        }
    }

    @Nested
    @DisplayName("when new with all args constructor")
    class WhenNewAllArgsConstructor
    {
        @BeforeEach
        void createNew()
        {
            book = new BookDto(TEST_SID, TEST);
        }

        @Test
        @DisplayName("initialized values in Book()")
        void defaults()
        {
            assertThat(book).hasFieldOrPropertyWithValue("id", TEST_SID);
            assertThat(book).hasFieldOrPropertyWithValue("book", TEST);
        }

        @Test
        @DisplayName("Equals for class Book and hashCode")
        void testEquals()
        {
            assertNotEquals(new BookDto(), book);
            BookDto expected = new BookDto(TEST_SID, TEST);
            assertEquals(expected.hashCode(), book.hashCode());
            assertEquals(expected, book);
        }

        @Test
        @DisplayName("The length of string from Book::toString is great than zero")
        void testToString()
        {
            assertTrue(book.toString().length() > 0);
        }
    }

    @Nested
    @DisplayName("converters")
    class Converters
    {
        @Test
        void constructFromBook()
        {
            Book data = new Book(TEST_ID, TEST, null);
            BookDto expected = new BookDto(TEST_SID, TEST);
            book = new BookDto(data);
            assertEquals(expected, book);
        }

        @Test
        void createBook()
        {
            book = new BookDto(TEST_SID, TEST);
            Book expected = new Book(TEST_ID, TEST, null);
            Book test = book.createBook(null);
            assertEquals(expected, test);
        }

        @Test
        void constructFromNull()
        {
            //noinspection ConstantConditions
            assertThrows(AssertionError.class,() -> new BookDto(null));
        }

        @Test
        void updateFromNull()
        {
            book = new BookDto();
            assertThrows(AssertionError.class,() -> book.updateBook(null));
        }
    }
    */
}