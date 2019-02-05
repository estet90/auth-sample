package ru.kononov.authsample.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

    private String login;
    private String password;
    private String email;

}
