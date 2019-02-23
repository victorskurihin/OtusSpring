package ru.otus.homework.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.homework.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao extends CrudRepository<Genre, Long>
{
    Optional<Genre> findByGenre(String genre);

    List<Genre> findAll();

    void deleteById(long id);
}
