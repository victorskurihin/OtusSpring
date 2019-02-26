package ru.otus.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework.models.dto.BookDto;
import ru.otus.homework.services.DatabaseService;

import java.util.List;
import java.util.stream.Collectors;

import static ru.otus.homework.controllers.Constants.REST_API;
import static ru.otus.homework.controllers.Constants.REST_V1_BOOKS;

@RestController
public class BooksRestController
{
    private DatabaseService databaseService;

    @Autowired
    public BooksRestController(DatabaseService databaseService)
    {
        this.databaseService = databaseService;
    }

    @GetMapping(REST_API + REST_V1_BOOKS)
    public List<BookDto> getBooks()
    {
        return databaseService.getAllBooks()
            .stream()
            .map(BookDto::new)
            .collect(Collectors.toList());
    }

    @PutMapping(REST_API + REST_V1_BOOKS)
    public String updateBook(@RequestBody BookDto book)
    {
        System.err.println("book = " + book);
        return "HTTP PUT was called";
    }
}
