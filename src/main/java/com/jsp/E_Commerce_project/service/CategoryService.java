package com.jsp.E_Commerce_project.service;

import com.jsp.E_Commerce_project.dto.category.CategoryResponseDto;
import com.jsp.E_Commerce_project.dto.category.CreateCategoryRequestDto;
import com.jsp.E_Commerce_project.dto.category.CreateCategoryResponseDto;
import com.jsp.E_Commerce_project.dto.category.UpdateCategoryRequestDto;

import java.util.List;

public interface CategoryService {

    // Create category
    CreateCategoryResponseDto createCategory(CreateCategoryRequestDto request);

    // Get all categories
    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto updateCategory(Long categoryId, UpdateCategoryRequestDto request);

    // Delete category
    String deleteCategory(Long categoryId);
}
