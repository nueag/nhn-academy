package com.nhnacademy.shoppingmall.model;

import java.math.BigDecimal;

public interface ProductDto {
    int getProductId();

    String getProductName();

    String getProductImage();

    BigDecimal getPrice();

    long getStock();

    String getProductInfo();

}
