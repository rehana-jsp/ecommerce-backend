package com.jsp.E_Commerce_project.service.imp;


import com.jsp.E_Commerce_project.exception.CategoryNotFound;
import com.jsp.E_Commerce_project.repository.ProductRepository;
import org.springframework.stereotype.Service;

import com.jsp.E_Commerce_project.dto.category.*;
import com.jsp.E_Commerce_project.model.Category;
import com.jsp.E_Commerce_project.model.Product;
import com.jsp.E_Commerce_project.repository.CategoryRepository;
import com.jsp.E_Commerce_project.service.CategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public CreateCategoryResponseDto createCategory(CreateCategoryRequestDto request) {

        if (categoryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Category already exists");
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() ->
                            new CategoryNotFound("Parent category not found"));

            category.setParent(parent);
        }

        Category saved = categoryRepository.save(category);
        CreateCategoryResponseDto createCategoryResponseDto = objectMapper.convertValue(saved, CreateCategoryResponseDto.class);
        createCategoryResponseDto.setParentId(saved.getParent() != null ? saved.getParent().getId() : null);
        return createCategoryResponseDto;
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(category -> objectMapper.convertValue(category, CategoryResponseDto.class))
                .toList();
    }



    @Override
    public CategoryResponseDto updateCategory(Long categoryId, UpdateCategoryRequestDto request) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFound("Category not found with id: " + categoryId));

        if (categoryRepository.existsByNameIgnoreCase(request.getName())
                && !category.getName().equalsIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Category name already exists");
        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        if (request.getParentId() != null) {

            if (request.getParentId().equals(categoryId)) {
                throw new IllegalArgumentException("Category cannot be its own parent");
            }

            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() ->
                            new CategoryNotFound("Parent category not found"));

            category.setParent(parent);

        }

        Category updated = categoryRepository.save(category);
        CategoryResponseDto categoryResponseDto = objectMapper.convertValue(updated, CategoryResponseDto.class);
        categoryResponseDto.setParentId(updated.getParent() != null ? updated.getParent().getId() : null);
        return categoryResponseDto;
    }

    @Transactional
    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFound("Category not found with id: " + categoryId));

        if (category.getSubcategories() != null
                && !category.getSubcategories().isEmpty()) {
            throw new IllegalStateException("Cannot delete category because it has subcategories");
        }

        if (productRepository.existsByCategoryId(categoryId)) {
            throw new IllegalStateException("Cannot delete category because products are associated with it");
        }

        categoryRepository.delete(category);

        return "Category deleted successfully";
    }
}
