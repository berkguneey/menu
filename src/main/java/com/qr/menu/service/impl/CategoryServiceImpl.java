package com.qr.menu.service.impl;

import com.qr.menu.constant.ErrorConstants;
import com.qr.menu.dto.AddCategoryDto;
import com.qr.menu.dto.CategoryDto;
import com.qr.menu.entity.Category;
import com.qr.menu.exception.BusinessException;
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
            throw new BusinessException(ErrorConstants.ERR101);
        }
        return categoryOpt.get();
    }

    @Override
    public CategoryDto addCategory(AddCategoryDto request) {
        Optional<Category> categoryOpt = repository.findByName(request.getName());
        if (categoryOpt.isPresent()) {
            throw new BusinessException(ErrorConstants.ERR102);
        }
        Category category = mapper.toCategory(request);
        return mapper.toCategoryDto(repository.save(category));
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = repository.findAll();
        if (categories.isEmpty()) {
            throw new BusinessException(ErrorConstants.ERR101);
        }
        return mapper.toCategoryDtos(categories);
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = getOne(id);
        return mapper.toCategoryDto(category);
    }

}
