package ru.otus.homework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.LibraryUsersDao;
import ru.otus.homework.models.LibraryUser;

@Service
public class LibraryUsersDetailsService implements UserDetailsService
{

    private final LibraryUsersDao libraryUsersDao;

    @Autowired
    public LibraryUsersDetailsService(LibraryUsersDao libraryUsersDao)
    {
        this.libraryUsersDao = libraryUsersDao;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
    {
        LibraryUser user = libraryUsersDao.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return new LibraryUserDetails(user);
    }
}
