package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.model.Product;
import com.nhnacademy.shoppingmall.model.ProductDto;
import com.nhnacademy.shoppingmall.repository.ProductCategoryRepository;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.service.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getProductsByCategory(Pageable pageable, int categoryId) {
        return productCategoryRepository.getProductByCategoryId(pageable, categoryId);
    }

    @Override
    public Optional<Product> findById(int productId) {

        return productRepository.findById(productId);
    }

    @Override
    public void saveProduct(Product product) {
        if (productRepository.countByProductId(product.getProductId()) < 1) {
            productRepository.save(product);
        } else {
            throw new ProductAlreadyExistsException(product.getProductId());
        }
    }


    @Override
    public void deleteProduct(int productId) { //카테고리도 삭제가 되는지 모르겠음
        if (productRepository.countByProductId(productId) == 1) {
            productRepository.deleteByProductId(productId);
        } else {
            throw new ProductNotFoundException(productId);
        }
    }

    @Override
    public Page<Product> findAllPage(Pageable pageable) {
        return productRepository.getAllBy(pageable);
    }

    @Override
    public ProductDto getProductDetailByProductId(int productId) {
        return productRepository.findProductDtoByProductId(productId);
    }


}
