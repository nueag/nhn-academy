package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.Product;
import com.nhnacademy.shoppingmall.model.ProductDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends ProductRepositoryCustom, JpaRepository<Product, Integer> {
    List<Product> findAll();

    List<Product> findAllByProductId(int productId);

    Page<Product> getAllBy(Pageable pageable);

    ProductDto findProductDtoByProductId(int productId);

    Optional<Product> findByProductId(int productId);

    void deleteByProductId(int productId);

    int countByProductId(int productId);

}
