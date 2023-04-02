package com.qr.menu.service;

import com.qr.menu.dto.RegisterDto;
import com.qr.menu.entity.CustomUserDetails;

public interface ICustomUserService {

    RegisterDto register(RegisterDto request);

    CustomUserDetails findByUsername(String username);

}
