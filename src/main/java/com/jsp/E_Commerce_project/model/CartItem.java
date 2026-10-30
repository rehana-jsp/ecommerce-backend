package com.jsp.E_Commerce_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cartItems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "cartItems_seq")
    @SequenceGenerator(name = "cartItems_seq",initialValue = 1,allocationSize = 1)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Cart cart;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Product product;
}
