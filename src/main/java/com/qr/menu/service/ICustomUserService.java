package com.qr.menu.service;

import com.qr.menu.dto.request.RegisterRequest;
import com.qr.menu.entity.CustomUserDetails;

public interface ICustomUserService {

    RegisterRequest register(RegisterRequest request);

    CustomUserDetails findByUsername(String username);

}
