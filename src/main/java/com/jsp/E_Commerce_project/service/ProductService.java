package com.jsp.E_Commerce_project.service;

import com.jsp.E_Commerce_project.dto.product.ProductResponseDto;

import java.util.List;

public interface ProductService {

//     Get products by category ID
    List<ProductResponseDto> getProductsByCategoryId(Long categoryId);
}
