package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.Product;
import com.nhnacademy.shoppingmall.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    int countBy();

    ProductCategory save(ProductCategory productCategory);

    @Query("SELECT pc.product FROM ProductCategory pc WHERE pc.category.categoryId = :categoryId")
    Page<Product> getProductByCategoryId(Pageable pageable, @Param("categoryId") int categoryId);

}
