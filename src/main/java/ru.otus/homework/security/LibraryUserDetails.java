package ru.otus.homework.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.otus.homework.models.LibraryUser;

import java.util.Collection;
import java.util.Collections;

public class LibraryUserDetails implements UserDetails
{

    private final LibraryUser libraryUser;

    public LibraryUserDetails(LibraryUser libraryUser)
    {
        this.libraryUser = libraryUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.singletonList((GrantedAuthority) () -> "ROLE_USERS");
    }

    @Override
    public String getPassword()
    {
        return libraryUser.getPassword();
    }

    @Override
    public String getUsername()
    {
        return libraryUser.getLogin();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
