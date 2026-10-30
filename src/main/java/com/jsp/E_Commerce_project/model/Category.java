package com.jsp.E_Commerce_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "categories_seq")
    @SequenceGenerator(name = "categories_seq",initialValue = 1,allocationSize = 1)
    private long id;

    private String name;
    private String description;

    // Many subcategories can have one parent category
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    // One category can have many subcategories
    @JsonIgnore
    @OneToMany(mappedBy = "parent")
    private List<Category> subcategories = new ArrayList<>();
}
