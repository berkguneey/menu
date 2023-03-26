package com.qr.menu.mapper;

import com.qr.menu.dto.AddProductDto;
import com.qr.menu.dto.ProductDto;
import com.qr.menu.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductMapper {

    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDtos(List<Product> products);

    Product toProduct(ProductDto productDto);

    Product toProduct(AddProductDto addProductDto);

}
