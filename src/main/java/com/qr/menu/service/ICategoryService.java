package com.qr.menu.service;

import com.qr.menu.dto.AddCategoryDto;
import com.qr.menu.dto.CategoryDto;
import com.qr.menu.entity.Category;

import java.util.List;

public interface ICategoryService {

    Category getOne(Long id);

    CategoryDto addCategory(AddCategoryDto request);

    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

}
