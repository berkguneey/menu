package com.qr.menu.service.impl;

import com.qr.menu.dto.CategoryDto;
import com.qr.menu.dto.request.AddCategoryRequest;
import com.qr.menu.entity.Category;
import com.qr.menu.mapper.CategoryMapper;
import com.qr.menu.repository.CategoryRepository;
import com.qr.menu.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public Category getOne(Long id) {
        Optional<Category> categoryOpt = repository.findById(id);
        if (!categoryOpt.isPresent()) {
            throw new RuntimeException("Not Found!");
        }
        return categoryOpt.get();
    }

    @Override
    public CategoryDto addCategory(AddCategoryRequest request) {
        List<Category> categories = repository.findByName(request.getName());
        if (!categories.isEmpty()) {
            throw new RuntimeException("Already Found!");
        }
        Category category = mapper.toCategory(request);
        return mapper.toCategoryDto(repository.save(category));
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = repository.findAll();
        if (categories.isEmpty()) {
            throw new RuntimeException("Not Found!");
        }
        return mapper.toCategoryDtos(categories);
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = getOne(id);
        return mapper.toCategoryDto(category);
    }

}
