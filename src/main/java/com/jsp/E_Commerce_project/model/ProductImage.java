package com.jsp.E_Commerce_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "productImages")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "productImages_seq")
    @SequenceGenerator(name = "productImages_seq",initialValue = 1,allocationSize = 1)
    private long id;

    private String imageUrl;
    private boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonIgnore
    private Product product;
}
