package com.qr.menu.service.impl;

import com.qr.menu.constant.ErrorConstants;
import com.qr.menu.dto.AddCategoryRequestDto;
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
        return repository.findById(id).orElseThrow(() -> new BusinessException(ErrorConstants.ERR101));
    }

    @Override
    public CategoryDto addCategory(AddCategoryRequestDto request) {
        Optional<Category> categoryOpt = repository.findByName(request.getName());
        if (categoryOpt.isPresent()) {
            throw new BusinessException(ErrorConstants.ERR102);
        }
        Category newCategory = mapper.toCategory(request);
        return mapper.toCategoryDto(repository.save(newCategory));
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
        return mapper.toCategoryDto(getOne(id));
    }

}
