package ru.otus.homework.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@ConfigurationProperties(prefix = "spring")
public class YamlSpringProperties
{
    private Properties datasource;

    public Properties getDatasource()
    {
        return datasource;
    }

    public void setDatasource(Properties datasource)
    {
        this.datasource = datasource;
    }
}
