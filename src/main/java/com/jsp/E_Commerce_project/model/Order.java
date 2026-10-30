package com.jsp.E_Commerce_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsp.E_Commerce_project.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq",initialValue = 1,allocationSize = 1)
    private long id;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Address address;


    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "order")
    @JsonIgnore
    private List<OrderItem> orderItems;


}
