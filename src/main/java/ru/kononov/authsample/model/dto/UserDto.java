package ru.kononov.authsample.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String login;
    private String password;
    private String email;

}
