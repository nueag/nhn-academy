package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.model.Product;
import com.nhnacademy.shoppingmall.model.ProductDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    List<Product> getProducts();

    Page<Product> getProductsByCategory(Pageable pageable, int categoryId);

    Optional<Product> findById(int productId);

    void saveProduct(Product product);

    void deleteProduct(int productId);

    Page<Product> findAllPage(Pageable pageable);

    ProductDto getProductDetailByProductId(int productId);
}
