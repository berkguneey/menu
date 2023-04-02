package com.qr.menu.service;

import com.google.zxing.WriterException;
import com.qr.menu.dto.*;

import java.io.IOException;
import java.util.List;

public interface IRestaurantOrchestrationService {

    List<ActiveMenuProductsResponseDto> findActiveMenuProductsByRestaurantId(Long restaurantId);

    RestaurantDto addRestaurant(AddRestaurantRequestDto request) throws IOException, WriterException;

    List<RestaurantDto> findAllRestaurants();

    List<RestaurantDto> findRestaurants();

    RestaurantDto findRestaurantByRestaurantId(Long restaurantId);

    List<MenuDto> findMenusByRestaurantId(Long restaurantId);

    MenuDto addMenuToRestaurant(Long restaurantId, AddMenuRequestDto request);

    List<MenuProductDto> findMenuProductsByRestaurantIdAndMenuId(Long restaurantId, Long menuId);

    ProductDto addProductToRestaurantAndMenu(Long restaurantId, Long menuId, AddProductRequestDto request);

}
