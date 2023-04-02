package com.qr.menu.controller;

import com.google.zxing.WriterException;
import com.qr.menu.dto.MenuDto;
import com.qr.menu.dto.MenuProductDto;
import com.qr.menu.dto.ProductDto;
import com.qr.menu.dto.RestaurantDto;
import com.qr.menu.dto.request.AddMenuRequest;
import com.qr.menu.dto.request.AddProductRequest;
import com.qr.menu.dto.request.AddRestaurantRequest;
import com.qr.menu.dto.response.ActiveMenuProductsResponse;
import com.qr.menu.service.IRestaurantOrchestrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestaurantController {

    private final IRestaurantOrchestrationService service;

    @GetMapping("/restaurants/{id}/active-menu/products")
    public ResponseEntity<List<ActiveMenuProductsResponse>> findActiveMenuProductsByRestaurantId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findActiveMenuProductsByRestaurantId(id));
    }

    @PostMapping("/admin/restaurants")
    public ResponseEntity<RestaurantDto> addRestaurant(@RequestBody AddRestaurantRequest request) throws IOException, WriterException {
        return ResponseEntity.ok(service.addRestaurant(request));
    }

    @GetMapping("/admin/restaurants")
    public ResponseEntity<List<RestaurantDto>> findAllRestaurants() {
        return ResponseEntity.ok(service.findAllRestaurants());
    }

    @GetMapping("/owner/restaurants")
    public ResponseEntity<List<RestaurantDto>> findRestaurants() {
        return ResponseEntity.ok(service.findRestaurants());
    }

    @GetMapping("/owner/restaurants/{restaurantId}")
    public ResponseEntity<RestaurantDto> findRestaurantByRestaurantId(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(service.findRestaurantByRestaurantId(restaurantId));
    }

    @GetMapping("/owner/restaurants/{restaurantId}/menus")
    public ResponseEntity<List<MenuDto>> findMenusByRestaurantId(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(service.findMenusByRestaurantId(restaurantId));
    }

    @PostMapping("/owner/restaurants/{restaurantId}/menus")
    public ResponseEntity<MenuDto> addMenuToRestaurant(@PathVariable Long restaurantId, @RequestBody AddMenuRequest request) {
        return ResponseEntity.ok(service.addMenuToRestaurant(restaurantId, request));
    }

    @GetMapping("/owner/restaurants/{restaurantId}/menus/{menuId}/products")
    public ResponseEntity<List<MenuProductDto>> findMenuProductsByRestaurantIdAndMenuId(@PathVariable Long restaurantId, @PathVariable Long menuId) {
        return ResponseEntity.ok(service.findMenuProductsByRestaurantIdAndMenuId(restaurantId, menuId));
    }

    @PostMapping("/owner/restaurants/{restaurantId}/menus/{menuId}/products")
    public ResponseEntity<ProductDto> addProductToRestaurantAndMenu(@PathVariable Long restaurantId, @PathVariable Long menuId, @RequestBody AddProductRequest request) {
        return ResponseEntity.ok(service.addProductToRestaurantAndMenu(restaurantId, menuId, request));
    }

}
