package ru.otus.homework.dao;

import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;
import ru.otus.homework.Main;
import ru.otus.homework.models.Author;
import ru.otus.homework.models.DataSet;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.otus.outside.DBConf.*;
import static ru.otus.outside.TestData.createAuthor1;
import static ru.otus.outside.TestData.createAuthor2;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class)
@DisplayName("Integration tests for data layer")
class RepositoryIntegrationTest
{
    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private ConnectionFactory connectionFactory;

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
    }

    private void insertAuthors(Author... authors)
    {
        this.authorDao.saveAll(Arrays.asList(authors))
            .as(StepVerifier::create)
            .expectNextCount(2)
            .verifyComplete();
    }

    @SafeVarargs
    private final <T extends DataSet> boolean isContains(T o, T... a)
    {
        for (T e : a) {
            if (e.equals(o)) return true;
        }
        return false;
    }

    @Test
    void findAll() throws IOException
    {
        Author author1 = createAuthor1();
        Author author2 = createAuthor2();

        insertAuthors(author1, author2);

        authorDao.findAll()
            .as(StepVerifier::create)
            .assertNext(author1::equals)
            .assertNext(author2::equals)
            .verifyComplete();

        authorDao.findAll().subscribe(a -> assertTrue(isContains(a, author1, author2)));
    }
}