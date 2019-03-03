package ru.otus.homework;

import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;
import ru.otus.homework.configs.ApplicationConfig;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.dao.ReviewDao;
import ru.otus.homework.models.*;
import ru.otus.outside.utils.R2DatabaseClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.otus.homework.configs.DBCreate.*;
import static ru.otus.outside.TestData.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Main.class, ApplicationConfig.class})
@DisplayName("Integration tests for data layer")
class R2dbcIntegrationTest
{
    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private ConnectionFactory connectionFactory;

    private <T extends DataSet> boolean isExists(T o, T ... a)
    {
        for (T e : a) {
            if (e.equals(o)) return true;
        }
        return false;
    }

    private void insertAuthors(Author... authors)
    {
        this.authorDao.saveAll(Arrays.asList(authors))
            .as(StepVerifier::create)
            .expectNextCount(2)
            .verifyComplete();
    }

    private void insertBooks(Book... books)
    {
        this.bookDao.saveAll(Arrays.asList(books))
            .as(StepVerifier::create)
            .expectNextCount(2)
            .verifyComplete();
    }

    private void insertGenres(Genre... genres)
    {
        this.genreDao.saveAll(Arrays.asList(genres))
            .as(StepVerifier::create)
            .expectNextCount(2)
            .verifyComplete();
    }

    private void insertReviews(Review... reviews)
    {
        this.reviewDao.saveAll(Arrays.asList(reviews))
            .as(StepVerifier::create)
            .expectNextCount(2)
            .verifyComplete();
    }

    @Nested
    @DisplayName("AuthorDao integration tests")
    class AuthorDaoTests
    {
        private Author author1;

        private Author author2;

        private Consumer<Author> authorXExists = e -> assertTrue(isExists(e, author1, author2));

        private Consumer<Author> author1Exists = e -> assertEquals(e, author1);

        private Consumer<Author> author2Exists = e -> assertEquals(e, author2);

        @BeforeEach
        void setUp()
        {
            // Hooks.onOperatorDebug();
            DatabaseClient client = DatabaseClient.create(connectionFactory);
            client.execute()
                .sql(TBL_CREATE_AUTHOR)
                .fetch()
                .rowsUpdated()
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

            author1 = createAuthor1();
            author2 = createAuthor2();

            insertAuthors(author1, author2);
        }

        @Test
        @DisplayName("inserts entities and find all of them")
        void findAll() throws IOException
        {
            authorDao.findAll()
                .as(StepVerifier::create)
                .assertNext(authorXExists)
                .assertNext(authorXExists)
                .verifyComplete();

            authorDao.findAll().subscribe(authorXExists);
        }

        @Test
        void annotatedQuery() throws IOException
        {
            authorDao.findByFirstName(author1.getFirstName())
                .as(StepVerifier::create)
                .assertNext(author1Exists)
                .verifyComplete();
            authorDao.findByFirstName(author2.getFirstName())
                .as(StepVerifier::create)
                .assertNext(author2Exists)
                .verifyComplete();

            authorDao.findByLastName(author1.getLastName())
                .as(StepVerifier::create)
                .assertNext(author1Exists)
                .verifyComplete();
            authorDao.findByLastName(author2.getLastName())
                .as(StepVerifier::create)
                .assertNext(author2Exists)
                .verifyComplete();

            authorDao.findByFirstNameAndLastName(author1.getFirstName(), author1.getLastName())
                .as(StepVerifier::create)
                .assertNext(author1Exists)
                .verifyComplete();
            authorDao.findByFirstNameAndLastName(author2.getFirstName(), author2.getLastName())
                .as(StepVerifier::create)
                .assertNext(author2Exists)
                .verifyComplete();
        }
    }

    @Nested
    @DisplayName("BookDao integration tests")
    class BookDaoTests
    {
        private Book book1;

        private Book book2;

        private Consumer<Book> bookXExists = e -> assertTrue(isExists(e, book1, book2));

        private Consumer<Book> book1Exists = e -> assertEquals(e, book1);

        private Consumer<Book> book2Exists = e -> assertEquals(e, book2);

        @BeforeEach
        void setUp()
        {
            R2DatabaseClient.createDb(connectionFactory);

            book1 = createBook1();
            book2 = createBook2();

            insertBooks(book1, book2);
        }

        @Test
        @DisplayName("inserts entities and find all of them")
        void findAll() throws IOException
        {
            bookDao.findAll()
                .as(StepVerifier::create)
                .assertNext(bookXExists)
                .assertNext(bookXExists)
                .verifyComplete();

            bookDao.findAll().subscribe(bookXExists);
        }

        @Test
        void annotatedQuery() throws IOException
        {
            bookDao.findByTitle(book1.getTitle())
                .as(StepVerifier::create)
                .assertNext(book1Exists)
                .verifyComplete();
            bookDao.findByTitle(book2.getTitle())
                .as(StepVerifier::create)
                .assertNext(book2Exists)
                .verifyComplete();
        }
    }

    @Nested
    @DisplayName("GenreDao integration tests")
    class GenreDaoTests
    {
        private Genre genre1;

        private Genre genre2;

        private Consumer<Genre> genreExists = e -> assertTrue(isExists(e, genre1, genre2));

        @BeforeEach
        void setUp()
        {
            DatabaseClient client = DatabaseClient.create(connectionFactory);
            client.execute()
                .sql(TBL_CREATE_GENRE)
                .fetch()
                .rowsUpdated()
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

            genre1 = createGenre1();
            genre2 = createGenre2();

            insertGenres(genre1, genre2);
        }

        @Test
        @DisplayName("inserts entities and find all of them")
        void findAll() throws IOException
        {
            genreDao.findAll()
                .as(StepVerifier::create)
                .assertNext(genreExists)
                .assertNext(genreExists)
                .verifyComplete();

            genreDao.findAll().subscribe(genreExists);
        }

        @Test
        void annotatedQuery() throws IOException
        {
            genreDao.findByValue(genre1.getValue())
                .as(StepVerifier::create)
                .assertNext(e-> assertEquals(e, genre1))
                .verifyComplete();
        }
    }

    @Nested
    @DisplayName("ReviewDao integration tests")
    class ReviewDaoTests
    {
        private Review review1;

        private Review review2;

        private Consumer<Review> reviewXExists = e -> assertTrue(isExists(e, review1, review2));

        private Consumer<Review> review1Exists = e -> assertEquals(e, review1);

        private Consumer<Review> review2Exists = e -> assertEquals(e, review2);

        @BeforeEach
        void setUp()
        {
            R2DatabaseClient.createDb(connectionFactory);

            review1 = createReview1();
            review2 = createReview2();

            insertReviews(review1, review2);
        }

        @Test
        @DisplayName("inserts entities and find all of them")
        void findAll() throws IOException
        {
            reviewDao.findAll()
                .as(StepVerifier::create)
                .assertNext(reviewXExists)
                .assertNext(reviewXExists)
                .verifyComplete();

            reviewDao.findAll().subscribe(reviewXExists);
        }

        @Test
        void annotatedQuery() throws IOException
        {
            reviewDao.countByBookId(review1.getBook().getId())
                .subscribe(count -> assertEquals(1L, count.longValue()));
            reviewDao.countByBookId(review2.getBook().getId())
                .subscribe(count -> assertEquals(1L, count.longValue()));
            reviewDao.countByBookId((long) Integer.MAX_VALUE)
                .subscribe(count -> assertEquals(0L, count.longValue()));

            reviewDao.findByReview(review1.getReview())
                .as(StepVerifier::create)
                .assertNext(review1Exists)
                .verifyComplete();
            reviewDao.findByReview(review2.getReview())
                .as(StepVerifier::create)
                .assertNext(review2Exists)
                .verifyComplete();

            reviewDao.findAllByBookId(review1.getBook().getId())
                .as(StepVerifier::create)
                .assertNext(review1Exists)
                .verifyComplete();
            reviewDao.findAllByBookId(review2.getBook().getId())
                .as(StepVerifier::create)
                .assertNext(review2Exists)
                .verifyComplete();
        }
    }
}