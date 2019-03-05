package ru.otus.homework.dao;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.otus.homework.models.Genre;

@Repository
public interface GenreDao extends ReactiveCrudRepository<Genre, Long>
{
    @Query("SELECT g FROM Genre g WHERE g.value = $1")
    Mono<Genre> findByValue(String value);

    // TODO void deleteById(long id);
}
