package ru.otus.homework.interfaces.dao;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static ru.otus.homework.TestConst.*;

@SpringBootApplication
@EntityScan(basePackages = {MODELS_PACKAGE})
@ComponentScan(basePackages = {DAO_PACKAGE})
public class SpringBootHomeworkTestDaoApplication {
    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).addScript("schema.sql").build();
    }

}
