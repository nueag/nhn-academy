package com.nhnacademy.shoppingmall.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
public class Order {

    @EmbeddedId
    private Pk pk;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "field")
    private LocalDateTime field;


    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {

        @NotNull
        @Column(name = "order_id")
        private int orderId;

        @NotNull
        @Column(name = "user_id")
        private String userId;

    }
}

