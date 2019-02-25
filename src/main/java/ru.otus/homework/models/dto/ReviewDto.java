package ru.otus.homework.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework.models.Book;
import ru.otus.homework.models.Review;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto
{
    private String id;

    private String bookId;

    private String review;

    public ReviewDto(Review entry)
    {
        assert entry != null;

        id = Long.toString(entry.getId());
        bookId = Long.toString(entry.getBook().getId());
        review = entry.getReview();
    }

    public Review updateReview(Review entry)
    {
        assert entry != null;

        entry.setId(Long.parseLong(id));
        entry.setReview(review);

        return entry;
    }

    public Review createReview(Book book)
    {
        Review review = new Review();
        review.setBook(book);

        return updateReview(review);
    }
}
