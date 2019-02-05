package ru.kononov.authsample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {

    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String doLogin() {
        log.info("huyak");
        return "registration";
    }

    @GetMapping(value = "/registration")
    public String registration() {
        log.info("huyak");
        return "registration";
    }

}
