package com.qr.menu.controller;

import com.qr.menu.dto.request.AddCategoryRequest;
import com.qr.menu.dto.response.CategoryResponse;
import com.qr.menu.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final ICategoryService service;

    @PostMapping("/owner/categories")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody AddCategoryRequest request) {
        CategoryResponse response = new CategoryResponse();
        response.setCategories(Collections.singletonList(service.addCategory(request)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/owner/categories")
    public ResponseEntity<CategoryResponse> findAllCategories() {
        CategoryResponse response = new CategoryResponse();
        response.setCategories(service.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/owner/categories/{id}")
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Long id) {
        CategoryResponse response = new CategoryResponse();
        response.setCategories(Collections.singletonList(service.findById(id)));
        return ResponseEntity.ok(response);
    }

}
