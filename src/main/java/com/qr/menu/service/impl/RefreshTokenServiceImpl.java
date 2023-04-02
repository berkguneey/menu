package com.qr.menu.service.impl;

import com.qr.menu.constant.ErrorConstants;
import com.qr.menu.entity.CustomUserDetails;
import com.qr.menu.entity.RefreshToken;
import com.qr.menu.exception.BusinessException;
import com.qr.menu.repository.RefreshTokenRepository;
import com.qr.menu.service.IRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    private final RefreshTokenRepository repository;

    @Value("${app.refreshExpirationInDays}")
    private Long refreshExpirationInDays;

    public String generateRefreshToken(CustomUserDetails user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(refreshExpirationInDays));
        refreshToken.setUser(user);
        refreshToken = repository.save(refreshToken);
        return refreshToken.getToken();
    }

    @Override
    public RefreshToken findByToken(String token) {
        return repository.findByToken(token).orElseThrow(() -> new BusinessException(HttpStatus.FORBIDDEN, ErrorConstants.ERR113));
    }

    @Override
    public void validateRefreshToken(RefreshToken refreshToken) {
        if (Objects.isNull(refreshToken)) {
            throw new BusinessException(HttpStatus.FORBIDDEN, ErrorConstants.ERR113);
        }
        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new BusinessException(HttpStatus.FORBIDDEN, ErrorConstants.ERR114);
        }
    }

    @Override
    public void deleteRefreshToken(String refreshToken) {
        repository.delete(findByToken(refreshToken));
    }

}
