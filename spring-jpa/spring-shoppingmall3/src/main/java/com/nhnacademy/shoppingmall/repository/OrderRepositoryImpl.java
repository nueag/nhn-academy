package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.Order;
import com.nhnacademy.shoppingmall.model.OrderDetail;
import com.nhnacademy.shoppingmall.model.QCart;
import com.nhnacademy.shoppingmall.model.QOrder;
import com.nhnacademy.shoppingmall.model.QProduct;
import com.nhnacademy.shoppingmall.model.QUser;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public OrderDetail getOrderDetailByOrderId(int orderId) {
        QCart cart = QCart.cart;
        QOrder order = QOrder.order;
        QUser user = QUser.user;
        QProduct product = QProduct.product;

        Tuple result = new JPAQueryFactory(entityManager)
                .select(product.productName, order.orderDate, user.userName, product.price)
                .from(order)
                .innerJoin(order.user, user)
                .innerJoin(order.cartList, cart)  // Join with cartList in Order entity
                .innerJoin(cart.product, product)
                .where(order.orderId.eq(orderId))
                .fetchFirst();

        return new OrderDetail(
                result.get(product.productName),
                result.get(user.userName),
                result.get(product.price)
        );
    }
}
