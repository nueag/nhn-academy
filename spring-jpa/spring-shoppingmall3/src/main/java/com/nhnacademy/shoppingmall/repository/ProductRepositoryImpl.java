package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.Product;
import com.nhnacademy.shoppingmall.model.QCategory;
import com.nhnacademy.shoppingmall.model.QProduct;
import com.nhnacademy.shoppingmall.model.QProductCategory;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {
    public ProductRepositoryImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> getProductByCategoryId(int categoryId) {
        QProduct product = QProduct.product;
        QProductCategory productCategory = QProductCategory.productCategory;
        QCategory category = QCategory.category;

        return from(productCategory)
                .innerJoin(productCategory.product, product)
                .innerJoin(productCategory.category, category)
                .where(category.categoryId.eq(categoryId))
                .select(product)
                .fetch();

    }
}
