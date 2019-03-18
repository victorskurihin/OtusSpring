package ru.otus.homework.acl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.homework.interfaces.services.DataStorageService;
import ru.otus.homework.models.Book;
import ru.otus.homework.models.Genre;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)
public class ACLTest {

    private static final String EROTIC_GENRE = "Эротика";

    @Autowired
    private DataStorageService dataStorageService;

    private void assertThatCountOfBooksOfEroticGenreReturnedByDataStorageServiceIs(int cnt) {
        List<Book> allBooks = dataStorageService.getAllBooks();
        assertThat(allBooks).isNotEmpty();

        List<Book> eroticBooks = allBooks.stream().filter(book -> book.getGenres().stream().map(Genre::getName).anyMatch(name -> name.equalsIgnoreCase(EROTIC_GENRE))).collect(Collectors.toList());
        assertThat(eroticBooks).hasSize(cnt);
    }

    @Test
    @WithMockUser(username = "AnyUser14", authorities = "ROLE_USERS")
    public void getAllBooksDoesNotReturnEroticContentForEveryOne() throws Exception {
        assertThatCountOfBooksOfEroticGenreReturnedByDataStorageServiceIs(0);
    }

    @Test
    @WithMockUser(username = "AnyUser18", authorities = "ROLE_USERS")
    public void getAllBooksReturnEroticContentForAdultUsers() throws Exception {
        assertThatCountOfBooksOfEroticGenreReturnedByDataStorageServiceIs(1);
    }
    @Test
    @WithMockUser(username = "Owner", authorities = "ROLE_USERS")
    public void getAllBooksReturnEroticContentForOwner() throws Exception {
        assertThatCountOfBooksOfEroticGenreReturnedByDataStorageServiceIs(1);
    }
}
