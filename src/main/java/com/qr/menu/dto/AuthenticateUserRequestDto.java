package com.qr.menu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateUserRequestDto {
    private String username;
    private String password;
}
