package com.qr.menu.controller;

import com.qr.menu.dto.AuthenticateUserRequestDto;
import com.qr.menu.dto.AuthenticateUserResponseDto;
import com.qr.menu.dto.LogoutRequest;
import com.qr.menu.dto.RefreshTokenRequest;
import com.qr.menu.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthenticationController {

    private final IAuthenticationService service;

    @PostMapping("/authentication/authenticateUser")
    public ResponseEntity<AuthenticateUserResponseDto> authenticateUser(@RequestBody AuthenticateUserRequestDto request) {
        return ResponseEntity.ok(service.authenticateUser(request));
    }

    @PostMapping("/authentication/refreshToken")
    public ResponseEntity<AuthenticateUserResponseDto> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(service.refreshToken(request));
    }

    @PostMapping("/authentication/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequest request) {
        service.logout(request);
        return ResponseEntity.ok().build();
    }

}
