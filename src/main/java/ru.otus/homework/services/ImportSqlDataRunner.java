package ru.otus.homework.services;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.otus.outside.utils.R2DatabaseClient;

@Service
public class ImportSqlDataRunner implements CommandLineRunner
{
    private ConnectionFactory connectionFactory;

    @Autowired
    public ImportSqlDataRunner(ConnectionFactory connectionFactory)
    {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void run(String... args) throws Exception
    {
        R2DatabaseClient.createDb(connectionFactory);
    }
}
