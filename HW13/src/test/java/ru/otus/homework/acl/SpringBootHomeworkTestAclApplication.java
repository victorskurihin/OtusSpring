package ru.otus.homework.acl;


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
@ComponentScan(basePackages = {INTERFACES_DAO_PACKAGE, DAO_PACKAGE, INTERFACES_SERVICES_PACKAGE, SERVICES_PACKAGE, SECURITY_PACKAGE, SECURITY_ACL_PACKAGE})
public class SpringBootHomeworkTestAclApplication {
    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).addScript("schema.sql").addScript("data.sql").build();
    }
}
