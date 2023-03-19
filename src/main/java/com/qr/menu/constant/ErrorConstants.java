package com.qr.menu.constant;

import lombok.Getter;

@Getter
public enum ErrorConstants {

    ERR101(101L, "Kategori bulunamadı.");

    private Long code;
    private String message;

    ErrorConstants(Long code, String message) {
        this.code = code;
        this.message = message;
    }

}
