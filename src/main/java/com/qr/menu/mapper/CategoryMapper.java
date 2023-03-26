package com.qr.menu.mapper;

import com.qr.menu.dto.AddCategoryDto;
import com.qr.menu.dto.CategoryDto;
import com.qr.menu.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryMapper {

    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDtos(List<Category> products);

    Category toCategory(CategoryDto categoryDto);

    Category toCategory(AddCategoryDto addCategoryDto);

}
