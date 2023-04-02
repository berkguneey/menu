package com.qr.menu.service;

import com.qr.menu.dto.request.AuthenticateUserRequest;
import com.qr.menu.dto.request.LogoutRequest;
import com.qr.menu.dto.request.RefreshTokenRequest;
import com.qr.menu.dto.response.AuthenticateUserResponse;

public interface IAuthenticationService {

    AuthenticateUserResponse authenticateUser(AuthenticateUserRequest request);

    AuthenticateUserResponse refreshToken(RefreshTokenRequest request);

    void logout(LogoutRequest request);

}
