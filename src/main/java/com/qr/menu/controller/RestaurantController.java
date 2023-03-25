package com.qr.menu.controller;

import com.google.zxing.WriterException;
import com.qr.menu.dto.request.AddMenuRequest;
import com.qr.menu.dto.request.AddProductRequest;
import com.qr.menu.dto.request.AddRestaurantRequest;
import com.qr.menu.dto.response.*;
import com.qr.menu.service.IRestaurantOrchestrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestaurantController {

    private final IRestaurantOrchestrationService service;

    @GetMapping("/restaurants/{id}/active-menu/products")
    public ResponseEntity<MenuProductCategoryBasedResponse> findActiveMenuProductsByRestaurantId(@PathVariable Long id) {
        MenuProductCategoryBasedResponse response = new MenuProductCategoryBasedResponse();
        response.setMenuProducts(service.findActiveMenuProductsByRestaurantId(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/restaurants")
    public ResponseEntity<RestaurantResponse> addRestaurant(@RequestBody AddRestaurantRequest request) throws IOException, WriterException {
        RestaurantResponse response = new RestaurantResponse();
        response.setRestaurants(Collections.singletonList(service.addRestaurant(request)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/restaurants")
    public ResponseEntity<RestaurantResponse> findAllRestaurants() {
        RestaurantResponse response = new RestaurantResponse();
        response.setRestaurants(service.findAllRestaurants());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/owner/restaurants")
    public ResponseEntity<RestaurantResponse> findRestaurants() {
        RestaurantResponse response = new RestaurantResponse();
        response.setRestaurants(service.findRestaurants());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/owner/restaurants/{restaurantId}")
    public ResponseEntity<RestaurantResponse> findRestaurantByRestaurantId(@PathVariable Long restaurantId) {
        RestaurantResponse response = new RestaurantResponse();
        response.setRestaurants(Collections.singletonList(service.findRestaurantByRestaurantId(restaurantId)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/owner/restaurants/{restaurantId}/menus")
    public ResponseEntity<MenuResponse> findMenusByRestaurantId(@PathVariable Long restaurantId) {
        MenuResponse response = new MenuResponse();
        response.setMenus(service.findMenusByRestaurantId(restaurantId));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/owner/restaurants/{restaurantId}/menus")
    public ResponseEntity<MenuResponse> addMenuToRestaurant(@PathVariable Long restaurantId, @RequestBody AddMenuRequest request) {
        MenuResponse response = new MenuResponse();
        response.setMenus(Collections.singletonList(service.addMenuToRestaurant(restaurantId, request)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/owner/restaurants/{restaurantId}/menus/{menuId}/products")
    public ResponseEntity<MenuProductResponse> findMenuProductsByRestaurantIdAndMenuId(@PathVariable Long restaurantId, @PathVariable Long menuId) {
        MenuProductResponse response = new MenuProductResponse();
        response.setMenuProducts(service.findMenuProductsByRestaurantIdAndMenuId(restaurantId, menuId));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/owner/restaurants/{restaurantId}/menus/{menuId}/products")
    public ResponseEntity<ProductResponse> addProductToRestaurantAndMenu(@PathVariable Long restaurantId, @PathVariable Long menuId, @RequestBody AddProductRequest request) {
        ProductResponse response = new ProductResponse();
        response.setProducts(Collections.singletonList(service.addProductToRestaurantAndMenu(restaurantId, menuId, request)));
        return ResponseEntity.ok(response);
    }

}
