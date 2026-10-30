package com.jsp.E_Commerce_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "orderItems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "orderItems_seq")
    @SequenceGenerator(name = "orderItems_seq",initialValue = 1,allocationSize = 1)
    private long id;
    private int quantity;
    private BigDecimal priceAtPurchase;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Order order;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;
}
