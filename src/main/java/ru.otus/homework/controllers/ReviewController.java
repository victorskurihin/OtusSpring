package ru.otus.homework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static ru.otus.homework.controllers.Constants.*;

@Controller
public class ReviewController
{
    @GetMapping(REQUEST_REVIEWS_LIST)
    public String booksList(@RequestParam long bookId, Model model)
    {
        model.addAttribute(MODEL_BOOK, bookId);
        model.addAttribute(MODEL_REVIEWS, REST_API + REST_V1_REVIEWS);

        return VIEW_REVIEWS_LIST;
    }
}
