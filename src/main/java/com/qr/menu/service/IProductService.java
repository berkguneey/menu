package com.qr.menu.service;

import com.qr.menu.dto.ProductDto;
import com.qr.menu.dto.request.AddProductRequest;
import com.qr.menu.entity.Restaurant;

import java.util.List;

public interface IProductService {

    ProductDto addProduct(Restaurant restaurant, Long menuId, AddProductRequest request);

    List<ProductDto> findAll();

    ProductDto findById(Long id);

}
