package ru.otus.homework.dao;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.otus.homework.models.Genre;

public interface GenreDao extends ReactiveCrudRepository<Genre, Long>
{
    @Query("SELECT genre_id, value FROM genre g WHERE g.value = $1")
    Mono<Genre> findByValue(String value);

    // TODO void deleteById(long id);
}
