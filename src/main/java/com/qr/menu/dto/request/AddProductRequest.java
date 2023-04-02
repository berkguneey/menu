package com.qr.menu.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AddProductRequest {
    private String name;
    private String description;
    private String imagePath;
    private Long categoryId;
    private BigDecimal price;
}
