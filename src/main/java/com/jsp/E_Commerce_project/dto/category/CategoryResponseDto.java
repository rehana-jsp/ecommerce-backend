package com.jsp.E_Commerce_project.dto.category;


import lombok.Data;

@Data
public class CategoryResponseDto {

    private Long id;
    private String name;
    private String description;
    private Long parentId;
}