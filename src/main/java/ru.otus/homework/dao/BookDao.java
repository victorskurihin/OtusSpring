package ru.otus.homework.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.homework.models.Book;

import java.util.List;

public interface BookDao extends CrudRepository<Book, Long>
{
    Book findByIsbn(String isbn);

    List<Book> findByTitle(String title);

    @Query(
        "SELECT DISTINCT b"
        + " FROM Book b"
        + " LEFT JOIN FETCH b.authors a"
        + " LEFT JOIN FETCH b.genre g"
    )
    List<Book> findAll();

    void deleteById(long id);
}
