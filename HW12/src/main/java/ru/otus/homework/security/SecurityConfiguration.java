package ru.otus.homework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static ru.otus.homework.controllers.MvcConsts.*;
import static ru.otus.homework.security.SecurityConst.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService libraryUsersDetailsService;
    private final String appKey;

    @Autowired
    public SecurityConfiguration(LibraryUsersDetailsService libraryUsersDetailsService, @Value("${application.key}") String appKey) {
        this.libraryUsersDetailsService = libraryUsersDetailsService;
        this.appKey = appKey;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests().antMatchers("/css/*", "/img/*", "/favicon.png").permitAll()
                .and()

                .authorizeRequests().antMatchers(REQUEST_LOGIN, REQUEST_LOGIN_PROCESSING).anonymous()
                .and()

                .authorizeRequests().antMatchers("/**").authenticated()
                .and()

                .formLogin()
                    .loginPage(REQUEST_LOGIN)
                    .loginProcessingUrl(REQUEST_LOGIN_PROCESSING)
                    .usernameParameter(USERNAME_PARAMETER)
                    .passwordParameter(PASSWORD_PARAMETER)
                    .defaultSuccessUrl("/", true)
                    .failureUrl(FAILURE_URL)
                .and()

                .rememberMe()
                .key(appKey)
                .userDetailsService(libraryUsersDetailsService)
                .alwaysRemember(true)
                .tokenValiditySeconds(60)
                ;
    }
}
