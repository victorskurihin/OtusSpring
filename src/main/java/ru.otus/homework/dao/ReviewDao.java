package ru.otus.homework.dao;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.models.Review;

@Repository
public interface ReviewDao extends ReactiveCrudRepository<Review, Long>

{
    @Query("SELECT COUNT(r) FROM review r WHERE r.book.id = $1")
    Mono<Long> countByBookId(Long id);

    @Query("SELECT DISTINCT r FROM Review r LEFT JOIN FETCH r.book b WHERE r.value = $1")
    Flux<Review> findByReview(String value);

    @Query("SELECT DISTINCT r FROM Review r LEFT JOIN FETCH r.book b WHERE b.id = $1")
    Flux<Review> findAllByBookId(Long id);
    /*
    @Query("SELECT DISTINCT r FROM Review r LEFT JOIN FETCH r.book b WHERE b.id = :id")
    List<Review> findAllByBookId(@Param("id") long id);

    @Modifying
    @Transactional
    void deleteById(long id);
    */
}
