package ru.otus.homework.dao;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.models.Author;

public interface AuthorDao extends ReactiveCrudRepository<Author, Long>
{
    @Query("SELECT author_id, first_name, last_name FROM author a WHERE a.first_name = $1")
    Flux<Author> findByFirstName(String firstName);

    @Query("SELECT author_id, first_name, last_name FROM author a WHERE a.last_name = $1")
    Flux<Author> findByLastName(String lastName);

    @Query(
        "SELECT author_id, first_name, last_name FROM author a"
        + " WHERE a.first_name = $1 AND  a.last_name = $2"
    )
    Mono<Author> findByFirstNameAndLastName(String firstName, String lastName);

    // TODO void deleteById(long id);
}
