package com.qr.menu.service;

import com.qr.menu.dto.AddProductDto;
import com.qr.menu.dto.ProductDto;
import com.qr.menu.entity.Restaurant;

import java.util.List;

public interface IProductService {

    ProductDto addProduct(Restaurant restaurant, Long menuId, AddProductDto request);

    List<ProductDto> findAll();

    ProductDto findById(Long id);

}
