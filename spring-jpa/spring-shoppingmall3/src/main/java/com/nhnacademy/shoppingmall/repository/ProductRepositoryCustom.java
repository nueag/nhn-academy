package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.Product;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProductRepositoryCustom {
    List<Product> getProductByCategoryId(int categoryId);
}
