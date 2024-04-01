package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByProductId(int productId);

    Optional<Product> findByProductId(int productId);

    Product save(Product product);

    void deleteByProductId(int productId);

    void updateBy(Product product);

    int countByProductId(int productId);

}
