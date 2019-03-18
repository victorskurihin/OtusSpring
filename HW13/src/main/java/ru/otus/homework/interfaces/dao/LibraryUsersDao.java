package ru.otus.homework.interfaces.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.homework.models.LibraryUser;

public interface LibraryUsersDao extends CrudRepository<LibraryUser, Long> {
    LibraryUser findByLogin(String login);
}
