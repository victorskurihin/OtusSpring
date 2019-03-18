package ru.otus.homework.configs;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.dialect.H2Dialect;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.function.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.dao.ReviewDao;

public class DatabaseConfig
{
    @Bean
    public H2ConnectionFactory h2ConnectionFactory() {
        return new H2ConnectionFactory(
            H2ConnectionConfiguration.builder()
                .url("mem:~/demodb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE;MODE=MySQL")
                .username("sa")
                .password("")
                .build());
    }

    @Bean
    public DatabaseClient databaseClient(H2ConnectionFactory connectionFactory) {
        return DatabaseClient.create(connectionFactory);
    }

    @Bean
    public AuthorDao repositoryAuthorDao(R2dbcRepositoryFactory factory) {
        return factory.getRepository(AuthorDao.class);
    }

    @Bean
    public BookDao repositoryBookDao(R2dbcRepositoryFactory factory) {
        return factory.getRepository(BookDao.class);
    }

    @Bean
    public GenreDao repositoryGenreDao(R2dbcRepositoryFactory factory) {
        return factory.getRepository(GenreDao.class);
    }

    @Bean
    public ReviewDao repositoryReviewDao(R2dbcRepositoryFactory factory) {
        return factory.getRepository(ReviewDao.class);
    }

    @Bean
    public RelationalMappingContext context() {
        return new RelationalMappingContext();
    }

    @Bean
    public R2dbcRepositoryFactory factory(DatabaseClient client, RelationalMappingContext context) {
        return new R2dbcRepositoryFactory(client, context, new DefaultReactiveDataAccessStrategy(new H2Dialect()));
    }
}
