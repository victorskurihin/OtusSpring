package ru.otus.homework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static ru.otus.homework.controllers.MvcConsts.*;

@Controller
public class LoginController {
    @GetMapping(REQUEST_LOGIN)
    public String viewLoginPage(@RequestParam(required = false, defaultValue = "false") boolean error){
        return VIEW_NAME_LOGIN;
    }
}
