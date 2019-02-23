package ru.otus.homework.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.homework.models.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewDao extends CrudRepository<Review, Long>
{
    @Query("SELECT DISTINCT r FROM Review r LEFT JOIN FETCH r.book b WHERE r.id = :id")
    Optional<Review> findById(@Param("id") long id);

    List<Review> findByReview(String value);

    @Query("SELECT DISTINCT r FROM Review r LEFT JOIN FETCH r.book b")
    List<Review> findAll();

    @Query("SELECT DISTINCT r FROM Review r LEFT JOIN FETCH r.book b WHERE b.id = :id")
    List<Review> findAllByBookId(@Param("id") long id);

    void deleteById(long id);
}
