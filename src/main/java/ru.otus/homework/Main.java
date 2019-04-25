package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

<<<<<<< HEAD
import ru.otus.homework.configs.YamlProperties;

@SpringBootApplication
@EnableConfigurationProperties(YamlProperties.class)
=======
import ru.otus.homework.configs.YamlApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties(YamlApplicationProperties.class)
>>>>>>> 3c01fc04fe97660798e73fdae8736623943ae16e
public class Main
{
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}
