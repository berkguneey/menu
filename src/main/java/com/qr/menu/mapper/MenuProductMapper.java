package com.qr.menu.mapper;

import com.qr.menu.dto.MenuProductDto;
import com.qr.menu.entity.MenuProduct;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MenuProductMapper {

    MenuProductDto toMenuProductDto(MenuProduct menuProduct);

    List<MenuProductDto> toMenuProductDtos(List<MenuProduct> menuProducts);

    MenuProduct toMenuProduct(MenuProductDto menuProductDto);

}
