package ru.otus.homework.models.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.otus.homework.models.Author;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.outside.utils.TestData.*;

@DisplayName("Class AuthorDto")
class AuthorDtoTest
{
    private AuthorDto author;

    @Test
    @DisplayName("is instantiated with new AuthorDto()")
    void isInstantiatedWithNew() {
        new AuthorDto();
    }

    @Nested
    @DisplayName("when new with empty constructor")
    class WhenNew
    {
        @BeforeEach
        void createNew()
        {
            author = new AuthorDto();
        }

        @Test
        @DisplayName("default values in Author()")
        void defaults()
        {
            assertThat(author).hasFieldOrPropertyWithValue("id", null);
            assertThat(author).hasFieldOrPropertyWithValue("firstName", null);
            assertThat(author).hasFieldOrPropertyWithValue("lastName", null);
        }

        @Test
        @DisplayName("Setter and getter for firstName")
        void testGetSetFirstName()
        {
            author.setFirstName(TEST);
            assertThat(author).hasFieldOrPropertyWithValue("firstName", TEST);
            assertEquals(TEST, author.getFirstName());
        }

        @Test
        @DisplayName("Setter and getter for lastName")
        void testGetSetLastName()
        {
            author.setLastName(TEST);
            assertThat(author).hasFieldOrPropertyWithValue("lastName", TEST);
            assertEquals(TEST, author.getLastName());
        }
    }

    @Nested
    @DisplayName("when new with all args constructor")
    class WhenNewAllArgsConstructor
    {
        @BeforeEach
        void createNew()
        {
            author = new AuthorDto(TEST_SID, TEST_FIRST_NAME, TEST_LAST_NAME);
        }

        @Test
        @DisplayName("initialized values in Author()")
        void defaults()
        {
            assertThat(author).hasFieldOrPropertyWithValue("id", TEST_SID);
            assertThat(author).hasFieldOrPropertyWithValue("firstName", TEST_FIRST_NAME);
            assertThat(author).hasFieldOrPropertyWithValue("lastName", TEST_LAST_NAME);
        }

        @Test
        @DisplayName("Equals for class Author and hashCode")
        void testEquals()
        {
            assertNotEquals(new AuthorDto(), author);
            AuthorDto expected = new AuthorDto(TEST_SID, TEST_FIRST_NAME, TEST_LAST_NAME);
            assertEquals(expected.hashCode(), author.hashCode());
            assertEquals(expected, author);
        }

        @Test
        @DisplayName("The length of string from Author::toString is great than zero")
        void testToString()
        {
            assertTrue(author.toString().length() > 0);
        }
    }

    @Nested
    @DisplayName("converters")
    class Converters
    {
        @Test
        void constructFromAuthor()
        {
            Author data = new Author(TEST_ID, TEST_FIRST_NAME, TEST_LAST_NAME);
            AuthorDto expected = new AuthorDto(TEST_SID, TEST_FIRST_NAME, TEST_LAST_NAME);
            author = new AuthorDto(data);
            assertEquals(expected, author);
        }

        @Test
        void createAuthor()
        {
            author = new AuthorDto(TEST_SID, TEST_FIRST_NAME, TEST_LAST_NAME);
            Author expected = new Author(TEST_ID, TEST_FIRST_NAME, TEST_LAST_NAME);
            Author test = author.createAuthor();
            assertEquals(expected, test);
        }

        @Test
        void constructFromNull()
        {
            //noinspection ConstantConditions
            assertThrows(AssertionError.class,() -> new AuthorDto(null));
        }

        @Test
        void updateFromNull()
        {
            author = new AuthorDto();
            assertThrows(AssertionError.class,() -> author.updateAuthor(null));
        }
    }
}