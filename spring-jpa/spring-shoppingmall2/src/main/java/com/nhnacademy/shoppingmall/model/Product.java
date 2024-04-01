package com.nhnacademy.shoppingmall.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "stock")
    private long stock;
    @Column(name = "product_image")
    private String productImage;
    @Column(name = "product_info")
    private String productInfo;
    @Column(name = "register_date")
    private LocalDateTime register_date;
    @Column(name = "latest_update_at")
    private LocalDateTime latestUpdateAt;

    public Product(int productId, String productName, double price, long stock, String productImage,
                   String productInfo,
                   LocalDateTime register_date, LocalDateTime latestUpdateAt) {
        this.productId = productId;
        this.productName = productName;
        this.price = BigDecimal.valueOf(price);
        this.stock = stock;
        this.productImage = productImage;
        this.productInfo = productInfo;
        this.register_date = register_date;
        this.latestUpdateAt = latestUpdateAt;
    }
}
