package com.qr.menu.service;

import com.qr.menu.dto.LoginDto;

public interface IAuthenticationService {

    LoginDto login(LoginDto request);

}
