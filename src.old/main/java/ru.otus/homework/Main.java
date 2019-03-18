package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

<<<<<<< HEAD
import ru.otus.homework.configs.YamlApplProperties;
import ru.otus.homework.configs.YamlSpringProperties;

@SpringBootApplication
@EnableConfigurationProperties({YamlApplProperties.class, YamlSpringProperties.class})
=======
import ru.otus.homework.configs.YamlApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties(YamlApplicationProperties.class)
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
public class Main
{
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}
