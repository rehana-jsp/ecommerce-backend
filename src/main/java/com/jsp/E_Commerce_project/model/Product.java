package com.jsp.E_Commerce_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "products_seq")
    @SequenceGenerator(name = "products_seq",initialValue = 1,allocationSize = 1)
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String brand;
    private boolean active;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductImage>  productImages;


}
