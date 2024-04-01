package com.nhnacademy.shoppingmall.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @NotNull
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;
}
