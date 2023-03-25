package com.qr.menu.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MenuProductDto {
    private Long id;
    private ProductDto product;
    private BigDecimal price;
}
