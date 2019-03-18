package ru.otus.homework.dao;

<<<<<<< HEAD
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.otus.homework.models.Book;

@Repository
public interface BookDao extends R2dbcRepository<Book, Long>
{
    /* TODO BROKEN
    @Query(
        "SELECT COUNT(b.book_id) FROM book b"
            + " LEFT OUTER JOIN author_isbn ai ON b.book_id = ai.book_id"
            + " LEFT OUTER JOIN author a ON ai.author_id = a.author_id"
            + " WHERE a.author_id = $1"
    )
    Mono<Long> countByAuthorId(Long id);
    */

    // TODO @Query("SELECT b FROM Book b LEFT JOIN FETCH b.authors a WHERE a.id = :id")
    // Flux<Book> findAllByAuthorId(Long id);

    @Query(
        "SELECT b.book_id, b.isbn, b.title, b.edition_number, b.copyright, g.genre_id, g.value"
        + " FROM book b LEFT JOIN genre g ON b.genre_id = g.genre_id"
        + " WHERE b.title = $1"
    )
    Flux<Book> findByTitle(String title);

    // TODO void deleteById(long id);
=======
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.models.Book;

import java.util.List;

@Transactional(readOnly = true)
public interface BookDao extends CrudRepository<Book, Long>
{
    @Query(
        value = "SELECT COUNT(b.book_id) FROM book b"
                + " LEFT OUTER JOIN author_isbn ai ON b.book_id = ai.book_id"
                + " LEFT OUTER JOIN author a ON ai.author_id = a.author_id"
                + " WHERE a.author_id = ?1",
        nativeQuery = true
    )
    long countByAuthorId(@Param("id") long id);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.authors a WHERE a.id = :id")
    List<Book> findAllByAuthorId(@Param("id") long id);

    List<Book> findByTitle(String title);

    @Query(
        "SELECT DISTINCT b"
        + " FROM Book b"
        + " LEFT JOIN FETCH b.authors a"
        + " LEFT JOIN FETCH b.genre g"
        + " ORDER BY b.isbn"
    )
    List<Book> findAll();

    @Modifying
    @Transactional
    void deleteById(long id);
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
}
