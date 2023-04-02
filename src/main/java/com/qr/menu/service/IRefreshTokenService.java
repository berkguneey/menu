package com.qr.menu.service;

import com.qr.menu.entity.CustomUserDetails;
import com.qr.menu.entity.RefreshToken;

public interface IRefreshTokenService {

    String generateRefreshToken(CustomUserDetails user);

    RefreshToken findByToken(String token);

    void validateRefreshToken(RefreshToken refreshToken);

    void deleteRefreshToken(String refreshToken);

}
