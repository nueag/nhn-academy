package com.nhnacademy.shoppingmall.repository;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.shoppingmall.config.RootConfig;
import com.nhnacademy.shoppingmall.config.WebConfig;
import com.nhnacademy.shoppingmall.model.Product;
import com.nhnacademy.shoppingmall.model.ProductDto;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ProductRepositoryTest {


    @Autowired
    private ProductRepository productRepository;

    @Test
    void findAllByProductIdTest() {
        List<Product> products = productRepository.findAllByProductId(2);
        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    void findAllTest() {
        List<Product> products = productRepository.findAll();
        assertThat(products.size()).isEqualTo(18);
    }

    @Test
    void getAllByPageableTest() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Product> page = productRepository.getAllBy(pageable);
        assertThat(page.getContent().size()).isEqualTo(9);
    }

    @Test
    void saveAndFlushTest() {
        Product product = new Product("아보카도", 3000, 300, null, null, null, null);
        Product save = productRepository.save(product);
        productRepository.flush();

        Optional<Product> result = productRepository.findByProductId(save.getProductId());

        assertThat(result.get().getProductName()).isEqualTo(product.getProductName());
    }

    @Test
    void findByProductIdTest() {
        Product product = new Product("아보카도", 3000, 300, null, null, null, null);
        Product save = productRepository.save(product);
        productRepository.flush();

        Optional<Product> result = productRepository.findByProductId(save.getProductId());

        assertThat(result.get().getProductName()).isEqualTo(product.getProductName());
    }

    @Test
    void deleteByProductIdTest() {
        Product product = new Product("아보카도", 3000, 300, null, null, null, null);
        Product save = productRepository.save(product);
        productRepository.flush();
        productRepository.deleteByProductId(save.getProductId());

        int count = productRepository.countByProductId(save.getProductId());

        assertThat(count).isEqualTo(0);
    }
    @Test
    void queryDslTest() {
        List<Product> products = productRepository.getProductByCategoryId(2);
        assertThat(products.size()).isEqualTo(6);
    }

    @Test
    void findProductDtoByProductIdTest() {
        Product product = new Product("아보카도", 3000, 300, null, null, null, null);
        Product save = productRepository.save(product);
        productRepository.flush();
        ProductDto productDto = productRepository.findProductDtoByProductId(save.getProductId());
        assertThat(productDto.getProductName()).isEqualTo(save.getProductName());

    }


}