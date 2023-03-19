package com.qr.menu.mapper;

import com.qr.menu.dto.MenuDto;
import com.qr.menu.dto.request.AddMenuRequest;
import com.qr.menu.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MenuMapper {

    MenuDto toMenuDto(Menu menu);

    List<MenuDto> toMenuDtos(List<Menu> menus);

    Menu toMenu(MenuDto menuDto);

    Menu toMenu(AddMenuRequest addMenuRequest);

}
