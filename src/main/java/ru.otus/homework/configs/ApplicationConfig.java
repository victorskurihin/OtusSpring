package ru.otus.homework.configs;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.dialect.H2Dialect;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.function.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.function.ReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.dao.ReviewDao;
import ru.otus.homework.services.*;

import static io.r2dbc.spi.ConnectionFactoryOptions.DRIVER;
import static io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD;
import static io.r2dbc.spi.ConnectionFactoryOptions.USER;

@Configuration
@ComponentScan
@EnableR2dbcRepositories
public class ApplicationConfig extends AbstractR2dbcConfiguration
{
    private YamlApplProperties yap;

    private YamlSpringProperties ysp;

    public ApplicationConfig(YamlApplProperties yamlApplProperties, YamlSpringProperties yamlSpringProperties)
    {
        ysp = yamlSpringProperties;
        yap = yamlApplProperties;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean("msg")
    public MessagesService msg()
    {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");

        return new MessagesServiceImpl(yap.getLocale(), ms);
    }

    @Bean
    public AuthorDao authorDao(R2dbcRepositoryFactory factory)
    {
        return factory.getRepository(AuthorDao.class);
    }

    @Bean
    public BookDao bookDao(R2dbcRepositoryFactory factory)
    {
        return factory.getRepository(BookDao.class);
    }

    @Bean
    public GenreDao genreDao(R2dbcRepositoryFactory factory)
    {
        return factory.getRepository(GenreDao.class);
    }

    @Bean
    public ReviewDao reviewDao(R2dbcRepositoryFactory factory)
    {
        return factory.getRepository(ReviewDao.class);
    }

    @Bean
    public R2dbcRepositoryFactory factory(DatabaseClient client,
                                          ReactiveDataAccessStrategy reactiveDataAccessStrategy,
                                          RelationalMappingContext context)
    {
        return new R2dbcRepositoryFactory(client, context, reactiveDataAccessStrategy);
    }

    @Bean
    public DatabaseClient databaseClient(ConnectionFactory factory)
    {
        return DatabaseClient.builder().connectionFactory(factory).build();
    }

    @Bean
    public ReactiveDataAccessStrategy reactiveDataAccessStrategy()
    {
        return new DefaultReactiveDataAccessStrategy(H2Dialect.INSTANCE);
    }

    @Bean
    @Override
    public ConnectionFactory connectionFactory()
    {
        H2ConnectionConfiguration configuration = H2ConnectionConfiguration.builder()
            .url("mem:db")
            .username("sa")
            .password("sa")
            .option("AUTOCOMMIT=true")
            .build();

        return new H2ConnectionFactory(configuration);

//        return ConnectionFactories.get(
//            ConnectionFactoryOptions.builder()
//                .option(DRIVER, "h2")
//                .option(PASSWORD, ysp.getDatasource().getProperty("password", "sa"))
//                .option(Option.valueOf("url"), ysp.getDatasource().getProperty("url", "mem:db"))
//                .option(USER, ysp.getDatasource().getProperty("username", "sa"))
//                .build()
//        );
    }
}
