package com.qr.menu.service;

import com.qr.menu.dto.AuthenticateUserRequestDto;
import com.qr.menu.dto.AuthenticateUserResponseDto;
import com.qr.menu.dto.LogoutRequest;
import com.qr.menu.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    AuthenticateUserResponseDto authenticateUser(AuthenticateUserRequestDto request);

    AuthenticateUserResponseDto refreshToken(RefreshTokenRequest request);

    void logout(LogoutRequest request);

}
