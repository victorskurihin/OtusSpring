package ru.otus.homework.configs;

<<<<<<< HEAD
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
=======
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
<<<<<<< HEAD
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
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
        yap = yamlApplProperties;
        ysp = yamlSpringProperties;
=======
import ru.otus.homework.services.*;

@Configuration
@ComponentScan
public class ApplicationConfig
{
    private YamlApplicationProperties yap;

    public ApplicationConfig(YamlApplicationProperties yaProperties) {
        yap = yaProperties;
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
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
<<<<<<< HEAD

    @Bean
    @Override
    public ConnectionFactory connectionFactory()
    {
        return ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
                .option(DRIVER, "h2")
                .option(PASSWORD, ysp.getDatasource().getProperty("password", "sa"))
                .option(Option.valueOf("url"), ysp.getDatasource().getProperty("url", "mem:db"))
                .option(USER, ysp.getDatasource().getProperty("username", "sa"))
                .build()
        );
    }
=======
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
}
