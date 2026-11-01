package com.jsp.E_Commerce_project.controller;


import com.jsp.E_Commerce_project.dto.category.CreateCategoryRequestDto;
import com.jsp.E_Commerce_project.dto.category.CreateCategoryResponseDto;
import com.jsp.E_Commerce_project.dto.category.UpdateCategoryRequestDto;
import com.jsp.E_Commerce_project.dto.category.CategoryResponseDto;

import com.jsp.E_Commerce_project.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CreateCategoryResponseDto> createCategory(@Valid @RequestBody CreateCategoryRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(  categoryService.createCategory(request) );
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {

        List<CategoryResponseDto> response =
                categoryService.getAllCategories();

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}/categoryId")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable("id") Long categoryId,
                                                              @Valid @RequestBody UpdateCategoryRequestDto request) {

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(categoryId, request));
    }

    @DeleteMapping("/{id}/categoryId")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategory(categoryId));
    }
}