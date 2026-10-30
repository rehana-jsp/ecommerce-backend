package com.jsp.E_Commerce_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "address_seq")
    @SequenceGenerator(name = "address_seq",initialValue = 1,allocationSize = 1)
    private long id;

    private String street;
    private String city;
    private String state;
    private String pincode;
    private String country;
    private boolean isDefault;


    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private User user;


    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private List<Order> order;
}
