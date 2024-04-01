package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.Cart;
import com.nhnacademy.shoppingmall.model.Category;
import com.nhnacademy.shoppingmall.model.Product;
import com.nhnacademy.shoppingmall.model.ProductCategory;
import java.sql.Connection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    int countBy();

    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> getProductCategoriesByCategoryId(int categoryId);

}
