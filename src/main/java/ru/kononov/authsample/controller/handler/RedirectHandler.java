package ru.kononov.authsample.controller.handler;

import org.springframework.stereotype.Component;

@Component
public class RedirectHandler {

    public void redirect(String url) {
        System.out.println(url);
    }

}
