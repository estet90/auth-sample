package ru.kononov.authsample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
