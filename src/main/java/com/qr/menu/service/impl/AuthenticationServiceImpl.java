package com.qr.menu.service.impl;

import com.qr.menu.dto.request.AuthenticateUserRequest;
import com.qr.menu.dto.request.LogoutRequest;
import com.qr.menu.dto.request.RefreshTokenRequest;
import com.qr.menu.dto.response.AuthenticateUserResponse;
import com.qr.menu.entity.CustomUserDetails;
import com.qr.menu.entity.RefreshToken;
import com.qr.menu.helper.JwtTokenProvider;
import com.qr.menu.service.IAuthenticationService;
import com.qr.menu.service.ICustomUserService;
import com.qr.menu.service.IRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final IRefreshTokenService refreshTokenService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ICustomUserService customUserService;

    @Override
    public AuthenticateUserResponse authenticateUser(AuthenticateUserRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails user = customUserService.findByUsername(authentication.getName());
        String accessToken = jwtTokenProvider.generateToken(user);
        String refreshToken = refreshTokenService.generateRefreshToken(user);
        return new AuthenticateUserResponse(user.getId(), user.getUsername(), accessToken, "Bearer ", refreshToken);
    }

    @Override
    public AuthenticateUserResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.findByToken(request.getRefreshToken());
        refreshTokenService.validateRefreshToken(refreshToken);
        CustomUserDetails user = refreshToken.getUser();
        String accessToken = jwtTokenProvider.generateToken(user);
        return new AuthenticateUserResponse(user.getId(), user.getUsername(), accessToken, "Bearer ", request.getRefreshToken());
    }

    @Override
    public void logout(LogoutRequest request) {
        refreshTokenService.deleteRefreshToken(request.getRefreshToken());
    }

}
