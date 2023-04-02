package com.qr.menu.service;

import com.qr.menu.dto.AddMenuRequestDto;
import com.qr.menu.dto.MenuDto;
import com.qr.menu.dto.MenuProductDto;
import com.qr.menu.entity.Menu;
import com.qr.menu.entity.Restaurant;

import java.util.List;

public interface IMenuService {

    Menu getOne(Long id);

    MenuDto addMenu(Restaurant restaurant, AddMenuRequestDto request);

    List<MenuDto> findAll();

    MenuDto findById(Long id);

    List<MenuDto> findAllByRestaurant(Restaurant restaurant);

    List<MenuProductDto> findProductsAndPricesByMenuId(Restaurant restaurant, Long menuId);

    List<MenuProductDto> findActiveMenuByRestaurant(Restaurant restaurant);

}
