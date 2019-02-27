package ru.otus.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.models.dto.BookDto;
import ru.otus.homework.models.dto.ResponseCountDto;
import ru.otus.homework.models.dto.ReviewDto;
import ru.otus.homework.services.DatabaseService;

import java.util.List;
import java.util.stream.Collectors;

import static ru.otus.homework.controllers.Constants.*;

@RestController
public class ReviewsRestController
{
    private DatabaseService databaseService;

    @Autowired
    public ReviewsRestController(DatabaseService databaseService)
    {
        this.databaseService = databaseService;
    }

    @GetMapping(REST_API + REST_V1_REVIEWS + "/{id}")
    public List<ReviewDto> getBooks(@PathVariable long id)
    {
        String bookId = Long.toString(id);

        return databaseService.getAllReviewsForBookById(id)
            .stream()
            .map(ReviewDto::new)
            .peek(reviewDto -> reviewDto.setBookId(bookId))
            .collect(Collectors.toList());
    }

    @GetMapping(REST_API + REST_V1_REVIEWS + "/count/by-book/{id}")
    public ResponseCountDto countReviewsByBookId(@PathVariable long id)
    {
        return new ResponseCountDto(databaseService.countReviewsByBookId(id));
    }
}
