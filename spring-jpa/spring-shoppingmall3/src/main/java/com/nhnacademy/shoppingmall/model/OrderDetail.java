package com.nhnacademy.shoppingmall.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class OrderDetail {
    private String productName;
    private String userName;
    private BigDecimal price;


    public OrderDetail(String productName, String userName, BigDecimal price) {
        this.productName = productName;
        this.userName = userName;
        this.price = price;
    }
}

