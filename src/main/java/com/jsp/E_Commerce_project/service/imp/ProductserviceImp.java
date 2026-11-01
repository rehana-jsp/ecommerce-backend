package com.jsp.E_Commerce_project.service.imp;

import com.jsp.E_Commerce_project.dto.product.ProductResponseDto;
import com.jsp.E_Commerce_project.exception.CategoryNotFound;
import com.jsp.E_Commerce_project.model.Product;
import com.jsp.E_Commerce_project.repository.CategoryRepository;
import com.jsp.E_Commerce_project.repository.ProductRepository;
import com.jsp.E_Commerce_project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductserviceImp  implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponseDto> getProductsByCategoryId(Long categoryId) {

        return null;
    }
}
