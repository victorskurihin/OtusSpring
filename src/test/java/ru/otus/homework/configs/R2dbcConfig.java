package ru.otus.homework.configs;

import io.r2dbc.h2.util.H2ServerExtension;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.annotation.PreDestroy;

import static io.r2dbc.spi.ConnectionFactoryOptions.DRIVER;
import static io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD;
import static io.r2dbc.spi.ConnectionFactoryOptions.USER;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfig extends AbstractR2dbcConfiguration
{
    @RegisterExtension
    static final H2ServerExtension SERVER = new H2ServerExtension();

    @NotNull
    @Bean
    @Override
    public ConnectionFactory connectionFactory()
    {
        /* TODO
        postgres.start();
        PostgresqlConnectionConfiguration config = PostgresqlConnectionConfiguration.builder()
            .host(postgres.getContainerIpAddress())
            .port(postgres.getFirstMappedPort())
            .database(postgres.getDatabaseName())
            .username(postgres.getUsername())
            .password(postgres.getPassword())
            .build();
        return new PostgresqlConnectionFactory(config);
        */

        return ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
                .option(DRIVER, "h2")
                .option(PASSWORD, SERVER.getPassword())
                .option(Option.valueOf("url"), SERVER.getUrl())
                .option(USER, SERVER.getUsername())
                .build()
        );
    }

    @PreDestroy
    void shutdown() {
        // TODO postgres.stop();
    }
}