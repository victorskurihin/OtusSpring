package ru.otus.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework.exceptions.AuthorNotFoundException;
import ru.otus.homework.exceptions.BadValueForAuthorIdException;
import ru.otus.homework.exceptions.BookNotFoundException;
import ru.otus.homework.models.Author;
import ru.otus.homework.models.Book;
import ru.otus.homework.models.dto.*;
import ru.otus.homework.services.DatabaseService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.otus.homework.controllers.Constants.*;

@RestController
public class AuthorsRestController
{
    private DatabaseService databaseService;

    @Autowired
    public AuthorsRestController(DatabaseService databaseService)
    {
        this.databaseService = databaseService;
    }

    @GetMapping(REST_API + REST_V1_AUTHORS + "/{id}")
    public List<AuthorBookIdDto> getAuthorsForBookId(@PathVariable long id)
    {
        String bookId = Long.toString(id);

        return databaseService.getAuthorsForBookId(id)
            .stream()
            .map(AuthorBookIdDto::new)
            .peek(author -> author.setBookId(bookId))
            .collect(Collectors.toList());
    }

    @PutMapping(REST_API + REST_V1_AUTHORS)
    public ResponseStatusDto updateAuthor(@RequestBody AuthorBookIdDto authorDto)
    {
        long authorId = Long.parseLong(authorDto.getId());
        if (authorId < 1) throw new BadValueForAuthorIdException();

        Optional<Author> optionalAuthor = databaseService.getAuthorById(authorId);
        optionalAuthor.ifPresent(author -> {
            authorDto.updateAuthor(author);
            databaseService.saveAuthor(author);
        });

        return new ResponseStatusOk();
    }

    @PostMapping(REST_API + REST_V1_AUTHORS)
    public ResponseStatusDto createAuthor(@RequestBody AuthorBookIdDto authorDto, HttpServletResponse response)
    {
        long authorId = Long.parseLong(authorDto.getId());
        if (authorId != 0) throw new BadValueForAuthorIdException();
        long bookId = Long.parseLong(authorDto.getBookId());
        Optional<Book> optionalBook = databaseService.getBookById(bookId);
        Book book = optionalBook.orElseThrow(BookNotFoundException::new);

        Author author = databaseService.getAuthorByFirstNameAndLastName(
            authorDto.getFirstName(), authorDto.getLastName()
        ).orElse(new Author(0L, authorDto.getFirstName(), authorDto.getLastName()));
        databaseService.addAuthorToBook(author, book);

        response.setStatus(HttpServletResponse.SC_CREATED);
        return new ResponseStatusCreated();
    }

    @DeleteMapping(REST_API + REST_V1_AUTHORS + "/{authorId}/from-book/{bookId}")
    public ResponseStatusDto deleteAuthorFromBook(@PathVariable long authorId, @PathVariable long bookId,
                                                  HttpServletResponse response)
    {
        Optional<Book> bookOptional = databaseService.getBookById(bookId);
        Book book = bookOptional.orElseThrow(BookNotFoundException::new);
        Optional<Author> authorOptional = databaseService.getAuthorById(authorId);
        Author author = authorOptional.orElseThrow(AuthorNotFoundException::new);
        book.getAuthors().remove(author);
        databaseService.saveBook(book);
        if (databaseService.countBooksByAuthorId(authorId) < 1) {
            databaseService.removeAuthor(authorId);
        }

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return new ResponseStatusNoContent();
    }
}
