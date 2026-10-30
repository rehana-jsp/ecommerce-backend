package com.jsp.E_Commerce_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jsp.E_Commerce_project.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@ToString(exclude = {"order","address","cart"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "users_seq")
    @SequenceGenerator(name = "users_seq",initialValue = 1,allocationSize = 1)
    private long id;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String phone;
    private boolean active;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Order> orders=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Address> address=new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Cart cart;
}
