package com.qr.menu.service.impl;

import com.google.zxing.WriterException;
import com.qr.menu.constant.ErrorConstants;
import com.qr.menu.dto.*;
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
        String username = auth.getName();
        return repository.findByIdAndUsername(id, username).orElseThrow(() -> new BusinessException(ErrorConstants.ERR109));
    }

    private Restaurant getOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(ErrorConstants.ERR109));
    }

    @Override
    public List<ActiveMenuProductsResponseDto> findActiveMenuProductsByRestaurantId(Long restaurantId) {
        List<MenuProductDto> menuProducts = menuService.findActiveMenuByRestaurant(getOne(restaurantId));

        return menuProducts.stream()
                .collect(Collectors.groupingBy(
                        mp -> mp.getProduct().getCategory(),
                        Collectors.mapping(mp -> mp, Collectors.toList())))
                .entrySet().stream()
                .map(entry -> {
                    ActiveMenuProductsResponseDto menuProductCategoryBased = new ActiveMenuProductsResponseDto();
                    menuProductCategoryBased.setCategory(entry.getKey());
                    menuProductCategoryBased.setMenuProducts(entry.getValue());
                    return menuProductCategoryBased;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RestaurantDto addRestaurant(AddRestaurantRequestDto request) throws IOException, WriterException {
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
        return mapper.toRestaurantDto(getOneByAuth(restaurantId));
    }

    @Override
    public List<MenuDto> findMenusByRestaurantId(Long restaurantId) {
        return menuService.findAllByRestaurant(getOneByAuth(restaurantId));
    }

    @Override
    public MenuDto addMenuToRestaurant(Long restaurantId, AddMenuRequestDto request) {
        return menuService.addMenu(getOneByAuth(restaurantId), request);
    }

    @Override
    public List<MenuProductDto> findMenuProductsByRestaurantIdAndMenuId(Long restaurantId, Long menuId) {
        return menuService.findProductsAndPricesByMenuId(getOneByAuth(restaurantId), menuId);
    }

    @Override
    public ProductDto addProductToRestaurantAndMenu(Long restaurantId, Long menuId, AddProductRequestDto request) {
        return productService.addProduct(getOneByAuth(restaurantId), menuId, request);
    }

}
