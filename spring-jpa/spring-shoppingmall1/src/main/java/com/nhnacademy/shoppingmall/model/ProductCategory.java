package com.nhnacademy.shoppingmall.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @EmbeddedId
    private Pk pk;

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    public static class Pk implements Serializable {

        @NotNull
        @Column(name = "product_id")
        private int productId;

        @NotNull
        @Column(name = "category_id")
        private int categoryId;

    }
}
