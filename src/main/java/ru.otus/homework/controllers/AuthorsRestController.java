package ru.otus.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.models.dto.AuthorBookIdDto;
import ru.otus.homework.services.DatabaseService;

import java.util.List;
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
}
