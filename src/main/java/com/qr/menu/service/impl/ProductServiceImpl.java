package com.qr.menu.service.impl;

import com.qr.menu.constant.ErrorConstants;
import com.qr.menu.dto.AddProductDto;
import com.qr.menu.dto.ProductDto;
import com.qr.menu.entity.MenuProduct;
import com.qr.menu.entity.Product;
import com.qr.menu.entity.Restaurant;
import com.qr.menu.exception.BusinessException;
import com.qr.menu.mapper.ProductMapper;
import com.qr.menu.repository.MenuProductRepository;
import com.qr.menu.repository.ProductRepository;
import com.qr.menu.service.ICategoryService;
import com.qr.menu.service.IMenuService;
import com.qr.menu.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final ICategoryService categoryService;
    private final IMenuService menuService;
    private final MenuProductRepository menuProductRepository;

    private Product getOne(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorConstants.ERR107));
    }

    @Override
    public ProductDto addProduct(Restaurant restaurant, Long menuId, AddProductDto request) {
        Optional<Product> productOpt = productRepository.findByNameAndRestaurantIdAndMenuId(request.getName(), restaurant.getId(), menuId);
        if (productOpt.isPresent()) {
            throw new BusinessException(ErrorConstants.ERR108);
        }
        Product newProduct = mapper.toProduct(request);
        newProduct.setCategory(categoryService.getOne(request.getCategoryId()));
        newProduct.setRestaurant(restaurant);
        newProduct = productRepository.save(newProduct);

        MenuProduct menuProduct = new MenuProduct();
        menuProduct.setMenu(menuService.getOne(menuId));
        menuProduct.setProduct(newProduct);
        menuProduct.setPrice(request.getPrice());
        menuProductRepository.save(menuProduct);

        return mapper.toProductDto(newProduct);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new BusinessException(ErrorConstants.ERR107);
        }
        return mapper.toProductDtos(products);
    }

    @Override
    public ProductDto findById(Long id) {
        return mapper.toProductDto(getOne(id));
    }

}
