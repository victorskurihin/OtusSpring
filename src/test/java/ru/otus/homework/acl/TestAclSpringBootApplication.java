package ru.otus.homework.acl;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@SpringBootApplication
@EntityScan(basePackages = {"ru.otus.homework.models"})
@ComponentScan(basePackages = {
    "ru.otus.homework.dao",
    "ru.otus.homework.services",
    "ru.otus.homework.security",
    "ru.otus.homework.acl"
})
public class TestAclSpringBootApplication
{
    @Bean
    public DataSource dataSource()
    {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2)
            .addScript("schema.sql")
            .addScript("data.sql")
            .build();
    }
}
