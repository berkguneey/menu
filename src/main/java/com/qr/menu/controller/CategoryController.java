package com.qr.menu.controller;

import com.qr.menu.dto.CategoryDto;
import com.qr.menu.dto.request.AddCategoryRequest;
import com.qr.menu.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final ICategoryService service;

    @PostMapping("/owner/categories")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody AddCategoryRequest request) {
        return ResponseEntity.ok(service.addCategory(request));
    }

    @GetMapping("/owner/categories")
    public ResponseEntity<List<CategoryDto>> findAllCategories() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/owner/categories/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

}
