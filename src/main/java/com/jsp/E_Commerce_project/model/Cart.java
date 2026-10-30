package com.jsp.E_Commerce_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "carts_seq")
    @SequenceGenerator(name = "carts_seq",initialValue = 1,allocationSize = 1)
    private long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private   User user;


    @OneToMany(mappedBy = "cart")
    @JsonIgnore
    private List<CartItem> cartItem=new ArrayList<>();
}
