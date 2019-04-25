package ru.otus.homework.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.homework.services.*;

@Configuration
@ComponentScan
public class ApplicationConfig
{
<<<<<<< HEAD
    private YamlProperties yp;

    public ApplicationConfig(YamlProperties yamlProperties) {
        yp = yamlProperties;
=======
    private YamlApplicationProperties yap;

    public ApplicationConfig(YamlApplicationProperties yaProperties) {
        yap = yaProperties;
>>>>>>> 3c01fc04fe97660798e73fdae8736623943ae16e
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

<<<<<<< HEAD
        return new MessagesServiceImpl(yp.getLocale(), ms);
=======
        return new MessagesServiceImpl(yap.getLocale(), ms);
>>>>>>> 3c01fc04fe97660798e73fdae8736623943ae16e
    }
}
