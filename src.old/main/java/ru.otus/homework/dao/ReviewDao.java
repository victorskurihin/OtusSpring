package ru.otus.homework.dao;

<<<<<<< HEAD
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.models.Review;

@Repository
public interface ReviewDao extends R2dbcRepository<Review, Long>

{
    @Query("SELECT COUNT(r) FROM review r WHERE r.book.id = $1")
    Mono<Long> countByBookId(Long id);

    @Query("SELECT DISTINCT r FROM Review r LEFT JOIN FETCH r.book b WHERE r.value = $1")
    Flux<Review> findByReview(String value);

    @Query("SELECT DISTINCT r FROM Review r LEFT JOIN FETCH r.book b WHERE b.id = $1")
    Flux<Review> findAllByBookId(Long id);
    /*
=======
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.models.Review;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface ReviewDao extends CrudRepository<Review, Long>
{
    @Query("SELECT COUNT(r) FROM Review r WHERE r.book.id = :id")
    long countByBookId(@Param("id") long id);

    @Query("SELECT DISTINCT r FROM Review r LEFT JOIN FETCH r.book b WHERE r.id = :id")
    Optional<Review> findById(@Param("id") long id);

    List<Review> findByReview(String value);

    @Query("SELECT DISTINCT r FROM Review r LEFT JOIN FETCH r.book b")
    List<Review> findAll();

>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
    @Query("SELECT DISTINCT r FROM Review r LEFT JOIN FETCH r.book b WHERE b.id = :id")
    List<Review> findAllByBookId(@Param("id") long id);

    @Modifying
    @Transactional
    void deleteById(long id);
<<<<<<< HEAD
    */
=======
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
}
