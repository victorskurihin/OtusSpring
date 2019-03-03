package ru.otus.homework.dao;

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
}
