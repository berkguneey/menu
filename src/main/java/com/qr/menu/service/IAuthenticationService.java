package com.qr.menu.service;

import com.qr.menu.dto.request.LoginRequest;
import com.qr.menu.dto.response.LoginResponse;

public interface IAuthenticationService {

    LoginResponse login(LoginRequest request);

}
