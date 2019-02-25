package ru.otus.homework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static ru.otus.homework.controllers.Constants.*;

@Controller
public class BookController
{
    @GetMapping("/")
    public String booksList(Model model)
    {
        model.addAttribute(MODEL_BOOKS, REST_API + REST_V1_BOOKS);
        model.addAttribute(MODEL_AUTHORS, REST_API + REST_V1_AUTHORS);

        return VIEW_BOOKS_LIST;
    }
}
