package com.nhnacademy.shoppingmall.model;

public interface CartDto {
    int getCartId();

    User getUser();

    Product getProduct();

    int getAmount();
}
