package com.qr.menu.controller;

import com.qr.menu.dto.LoginDto;
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

    @PostMapping("/authentication/login")
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto request) {
        return ResponseEntity.ok(service.login(request));
    }

}
