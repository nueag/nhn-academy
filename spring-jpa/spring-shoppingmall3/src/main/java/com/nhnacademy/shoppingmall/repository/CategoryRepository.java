package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();
}
