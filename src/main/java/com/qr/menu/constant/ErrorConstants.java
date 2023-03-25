package com.qr.menu.constant;

import lombok.Getter;

@Getter
public enum ErrorConstants {

    ERR101(101L, "Kategori bulunamadı."),
    ERR102(102L, "Bu kategori zaten tanımlı."),
    ERR103(103L, "Kullanıcı bulunamadı."),
    ERR104(104L, "Menü bulunamadı."),
    ERR105(105L, "Bu menü zaten tanımlı."),
    ERR106(106L, "Bir restoran maksimum 3 menü ekleyebilir."),
    ERR107(107L, "Ürün bulunamadı."),
    ERR108(108L, "Bu ürün zaten tanımlı."),
    ERR109(109L, "Restoran bulunamadı."),
    ERR110(110L, "Bu restoran zaten tanımlı.");

    private Long code;
    private String message;

    ErrorConstants(Long code, String message) {
        this.code = code;
        this.message = message;
    }

}
