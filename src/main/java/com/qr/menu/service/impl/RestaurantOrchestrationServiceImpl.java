package com.qr.menu.service.impl;

import com.google.zxing.WriterException;
import com.qr.menu.constant.ErrorConstants;
import com.qr.menu.dto.*;
import com.qr.menu.dto.request.AddMenuRequest;
import com.qr.menu.dto.request.AddProductRequest;
import com.qr.menu.dto.request.AddRestaurantRequest;
import com.qr.menu.entity.Restaurant;
import com.qr.menu.exception.BusinessException;
import com.qr.menu.helper.QRCodeGeneratorHelper;
import com.qr.menu.mapper.RestaurantMapper;
import com.qr.menu.repository.RestaurantRepository;
import com.qr.menu.service.IMenuService;
import com.qr.menu.service.IProductService;
import com.qr.menu.service.IRestaurantOrchestrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantOrchestrationServiceImpl implements IRestaurantOrchestrationService {

    private final RestaurantRepository repository;
    private final RestaurantMapper mapper;
    private final IMenuService menuService;
    private final IProductService productService;
    private final QRCodeGeneratorHelper qrCodeGeneratorHelper;

    private Restaurant getOneByAuth(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Restaurant> restaurantOpt = repository.findByIdAndUsername(id, auth.getName());
        if (!restaurantOpt.isPresent()) {
            throw new BusinessException(ErrorConstants.ERR109);
        }
        return restaurantOpt.get();
    }

    private Restaurant getOne(Long id) {
        Optional<Restaurant> restaurantOpt = repository.findById(id);
        if (!restaurantOpt.isPresent()) {
            throw new BusinessException(ErrorConstants.ERR109);
        }
        return restaurantOpt.get();
    }

    @Override
    public List<MenuProductCategoryBasedDto> findActiveMenuProductsByRestaurantId(Long restaurantId) {
        List<MenuProductDto> menuProducts = menuService.findActiveMenuByRestaurant(getOne(restaurantId));

        List<MenuProductCategoryBasedDto> menuProductCategoryBasedList = menuProducts.stream()
                .collect(Collectors.groupingBy(
                        mp -> mp.getProduct().getCategory(),
                        Collectors.mapping(mp -> mp, Collectors.toList())))
                .entrySet().stream()
                .map(entry -> {
                    MenuProductCategoryBasedDto menuProductCategoryBased = new MenuProductCategoryBasedDto();
                    menuProductCategoryBased.setCategory(entry.getKey());
                    menuProductCategoryBased.setMenuProducts(entry.getValue());
                    return menuProductCategoryBased;
                })
                .collect(Collectors.toList());

        return menuProductCategoryBasedList;
    }

    @Override
    @Transactional
    public RestaurantDto addRestaurant(AddRestaurantRequest request) throws IOException, WriterException {
        Optional<Restaurant> restaurantOpt = repository.findByNameAndEmailAndPhoneNumber(request.getName(), request.getEmail(), request.getPhoneNumber());
        if (restaurantOpt.isPresent()) {
            throw new BusinessException(ErrorConstants.ERR110);
        }
        Restaurant restaurant = mapper.toRestaurant(request);
        restaurant = repository.save(restaurant);
        String url = "http://localhost:8081/restaurants/" + restaurant.getId() + "/active-menu/products";
        qrCodeGeneratorHelper.generateQRCode(request.getName(), url, 350, 350);
        restaurant.setQrCode(qrCodeGeneratorHelper.getQRCode(url, 350, 350));
        return mapper.toRestaurantDto(restaurant);
    }

    @Override
    public List<RestaurantDto> findAllRestaurants() {
        List<Restaurant> restaurants = repository.findAll();
        if (restaurants.isEmpty()) {
            throw new BusinessException(ErrorConstants.ERR109);
        }
        return mapper.toRestaurantDtos(restaurants);
    }

    @Override
    public List<RestaurantDto> findRestaurants() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Restaurant> restaurants = repository.findAllByUsername(auth.getName());
        if (restaurants.isEmpty()) {
            throw new BusinessException(ErrorConstants.ERR109);
        }
        return mapper.toRestaurantDtos(restaurants);
    }

    @Override
    public RestaurantDto findRestaurantByRestaurantId(Long restaurantId) {
        Restaurant restaurant = getOneByAuth(restaurantId);
        return mapper.toRestaurantDto(restaurant);
    }

    @Override
    public List<MenuDto> findMenusByRestaurantId(Long restaurantId) {
        Restaurant restaurant = getOneByAuth(restaurantId);
        return menuService.findAllByRestaurant(restaurant);
    }

    @Override
    public MenuDto addMenuToRestaurant(Long restaurantId, AddMenuRequest request) {
        Restaurant restaurant = getOneByAuth(restaurantId);
        return menuService.addMenu(restaurant, request);
    }

    @Override
    public List<MenuProductDto> findMenuProductsByRestaurantIdAndMenuId(Long restaurantId, Long menuId) {
        Restaurant restaurant = getOneByAuth(restaurantId);
        return menuService.findProductsAndPricesByMenuId(restaurant, menuId);
    }

    @Override
    public ProductDto addProductToRestaurantAndMenu(Long restaurantId, Long menuId, AddProductRequest request) {
        Restaurant restaurant = getOneByAuth(restaurantId);
        return productService.addProduct(restaurant, menuId, request);
    }

}
