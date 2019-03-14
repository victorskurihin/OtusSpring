package ru.otus.homework.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static ru.otus.homework.TestConst.*;

@SpringBootApplication
@ComponentScan(basePackages = {INTERFACES_DAO_PACKAGE})
@ComponentScan(basePackages = {SECURITY_PACKAGE})
public class SpringBootHomeworkTestControllersApplication {

}
