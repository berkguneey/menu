package com.qr.menu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticateUserResponse {
    private Long id;
    private String username;
    private String accessToken;
    private String type;
    private String refreshToken;
}
