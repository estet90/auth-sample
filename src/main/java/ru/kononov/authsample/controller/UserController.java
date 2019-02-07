package ru.kononov.authsample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/login")
    public String login() {
        log.debug("login get huyak");
        return "login";
    }

    @PostMapping(value = "/login")
    public String doLogin(@Valid @ModelAttribute RedirectModel model, BindingResult result) {
        log.debug("login post huyak");
        if (!result.hasErrors()) {
            log.debug("login post huyak redirect");
            return "redirect:" + model.getContinue();
        }
        return "registration";
    }

    @GetMapping(value = "/registration")
    public String registration() {
        log.debug("registration get huyak");
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String doRegistration(@RequestParam("test") String test) {
        log.debug("registration post huyak");
        return "registration";
    }

    public static class RedirectModel {
        @Pattern(regexp = "^/([^/].*)?$")
        @NotBlank
        private String continueUrl;

        public void setContinue(String continueUrl) {
            this.continueUrl = continueUrl;
        }

        public String getContinue() {
            return continueUrl;
        }
    }

}
