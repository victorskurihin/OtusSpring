package ru.otus.homework.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.otus.homework.models.UserProfile;

import java.util.Collection;
import java.util.Collections;

public class UserProfileDetails implements UserDetails
{
    private final UserProfile userProfile;

    public UserProfileDetails(UserProfile userProfile)
    {
        this.userProfile = userProfile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.singletonList((GrantedAuthority) () -> "USERS");
    }

    @Override
    public String getPassword()
    {
        return userProfile.getPassword();
    }

    @Override
    public String getUsername()
    {
        return  userProfile.getLogin();
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
