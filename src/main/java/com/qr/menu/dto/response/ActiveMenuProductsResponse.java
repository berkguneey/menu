package com.qr.menu.dto.response;

import com.qr.menu.dto.CategoryDto;
import com.qr.menu.dto.MenuProductDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ActiveMenuProductsResponse {
    private CategoryDto category;
    private List<MenuProductDto> menuProducts;
}
