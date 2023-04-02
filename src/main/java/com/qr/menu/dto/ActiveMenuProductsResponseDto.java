package com.qr.menu.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ActiveMenuProductsResponseDto {
    private CategoryDto category;
    private List<MenuProductDto> menuProducts;
}
