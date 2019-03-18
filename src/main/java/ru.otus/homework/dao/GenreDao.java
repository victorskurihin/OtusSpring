package ru.otus.homework.dao;

<<<<<<< HEAD
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.otus.homework.models.Genre;

@Repository
public interface GenreDao extends R2dbcRepository<Genre, Long>
{
    @Query("SELECT genre_id, value FROM genre g WHERE g.value = $1")
    Mono<Genre> findByValue(String value);

    // TODO void deleteById(long id);
=======
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.models.Genre;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface GenreDao extends CrudRepository<Genre, Long>
{
    Optional<Genre> findByValue(String value);

    List<Genre> findAll();

    @Modifying
    @Transactional
    void deleteById(long id);
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
}
