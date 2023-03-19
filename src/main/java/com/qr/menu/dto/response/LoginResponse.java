package com.qr.menu.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String username;
    private String jwtType = "Bearer ";
    private String jwtToken;
}
