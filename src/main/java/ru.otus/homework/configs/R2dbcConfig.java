package ru.otus.homework.configs;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import static io.r2dbc.spi.ConnectionFactoryOptions.DRIVER;
import static io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD;
import static io.r2dbc.spi.ConnectionFactoryOptions.USER;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfig extends AbstractR2dbcConfiguration
{
    private YamlSpringProperties yp;

    public R2dbcConfig(YamlSpringProperties yamlSpringProperties) {
        yp = yamlSpringProperties;
    }

    @NotNull
    @Bean
    @Override
    public ConnectionFactory connectionFactory()
    {
        return ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
                .option(DRIVER, "h2")
                .option(PASSWORD, yp.getDatasource().getProperty("password", "sa"))
                .option(Option.valueOf("url"), yp.getDatasource().getProperty("url", "mem:db"))
                .option(USER, yp.getDatasource().getProperty("username", "sa"))
                .build()
        );
    }
}