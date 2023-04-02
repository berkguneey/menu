package com.qr.menu.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AddProductRequestDto {
    private String name;
    private String description;
    private String imagePath;
    private Long categoryId;
    private BigDecimal price;
}
