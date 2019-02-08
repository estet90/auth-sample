package ru.kononov.authsample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/login")
    public String login(@RequestParam(name = "redirect", required = false) String redirect) {
        log.debug("login get huyak");
        return "login";
    }

//    @PostMapping("/login")
//    public String doLogin(@RequestParam(name = "redirect", required = false) String redirect) {
//        log.debug("login post huyak");
//        return "login";
//    }

}
