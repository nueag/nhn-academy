package com.nhnacademy.shoppingmall.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(long productId){
        super(String.format("product not found:%l",productId));
    }
}
