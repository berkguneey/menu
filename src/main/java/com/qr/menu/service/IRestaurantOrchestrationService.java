package com.qr.menu.service;

import com.google.zxing.WriterException;
import com.qr.menu.dto.MenuDto;
import com.qr.menu.dto.MenuProductDto;
import com.qr.menu.dto.ProductDto;
import com.qr.menu.dto.RestaurantDto;
import com.qr.menu.dto.request.AddMenuRequest;
import com.qr.menu.dto.request.AddProductRequest;
import com.qr.menu.dto.request.AddRestaurantRequest;

import java.io.IOException;
import java.util.List;

public interface IRestaurantOrchestrationService {

    List<MenuProductDto> findActiveMenuProductsByRestaurantId(Long restaurantId);

    RestaurantDto addRestaurant(AddRestaurantRequest request) throws IOException, WriterException;

    List<RestaurantDto> findAllRestaurants();

    List<RestaurantDto> findRestaurants();

    RestaurantDto findRestaurantByRestaurantId(Long restaurantId);

    List<MenuDto> findMenusByRestaurantId(Long restaurantId);

    MenuDto addMenuToRestaurant(Long restaurantId, AddMenuRequest request);

    List<MenuProductDto> findMenuProductsByRestaurantIdAndMenuId(Long restaurantId, Long menuId);

    ProductDto addProductToRestaurantAndMenu(Long restaurantId, Long menuId, AddProductRequest request);

}
