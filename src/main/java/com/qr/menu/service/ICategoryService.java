package com.qr.menu.service;

import com.qr.menu.dto.CategoryDto;
import com.qr.menu.dto.request.AddCategoryRequest;
import com.qr.menu.entity.Category;

import java.util.List;

public interface ICategoryService {

    Category getOne(Long id);

    CategoryDto addCategory(AddCategoryRequest request);

    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

}
