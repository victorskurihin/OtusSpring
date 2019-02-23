package ru.otus.homework.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.homework.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao extends CrudRepository<Author, Long>
{
    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    List<Author> findAll();

    void deleteById(long id);
}
