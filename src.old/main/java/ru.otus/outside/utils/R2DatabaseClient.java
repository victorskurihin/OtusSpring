package ru.otus.outside.utils;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.data.r2dbc.function.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Map;

import static ru.otus.homework.configs.DBCreate.DDL_CREATE_OPERATIONS;

public class R2DatabaseClient
{
    public static void updateSql(DatabaseClient client, String sql)
    {
        client.execute()
            .sql(sql)
            .fetch()
            .rowsUpdated()
            .as(StepVerifier::create)
            .expectNextCount(1)
            .verifyComplete();
    }

    public static Flux<Map<String, Object>> selectSql(DatabaseClient client, String sql)
    {
        return client.execute()
            .sql(sql)
            .fetch()
            .all();
    }

    public static Flux<Long> countSql(DatabaseClient client, String sql)
    {
        return client.execute()
            .sql(sql)
            .map((r, md) -> r.get(0, Long.class))
            .all();
    }

    public static void createDb(ConnectionFactory connectionFactory)
    {
        DatabaseClient client = DatabaseClient.create(connectionFactory);

        for (String sql : DDL_CREATE_OPERATIONS) {
            updateSql(client, sql);
        }
    }
}
