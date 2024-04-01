package com.nhnacademy.shoppingmall.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carts")
public class Cart {

    @EmbeddedId
    private Pk pk;

    @Column(name = "amount")
    private int amount;


    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    public static class Pk implements Serializable {

        @NotNull
        @Column(name = "cart_id")
        private int cartId;

        @NotNull
        @Column(name = "user_id")
        private String userId;

        @NotNull
        @Column(name = "product_id")
        private int productId;

    }
}
