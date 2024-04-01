package com.nhnacademy.shoppingmall.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.shoppingmall.config.RootConfig;
import com.nhnacademy.shoppingmall.config.WebConfig;
import com.nhnacademy.shoppingmall.model.Product;
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
@Disabled
class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void test() {
        int size = productCategoryRepository.countBy();
        assertThat(size).isEqualTo(4);
    }

    @Test
    void getProductByCategoryIdTest() {
        Pageable pageable = PageRequest.of(0, 9);
        int categoryId = 1;
        Page<Product> productPage = productCategoryRepository.getProductByCategoryId(pageable, categoryId);
        assertThat(productPage.getContent().size()).isEqualTo(9);
    }


}