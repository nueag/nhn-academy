package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.OrderDetail;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface OrderRepositoryCustom {
    OrderDetail getOrderDetailByOrderId(int orderId);
}
