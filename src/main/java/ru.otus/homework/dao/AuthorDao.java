package ru.otus.homework.dao;

<<<<<<< HEAD
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.models.Author;

@Repository
public interface AuthorDao extends R2dbcRepository<Author, Long>
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
=======
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.models.Author;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface AuthorDao extends CrudRepository<Author, Long>
{
    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    List<Author> findAll();

    @Modifying
    @Transactional
    void deleteById(long id);
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
}
