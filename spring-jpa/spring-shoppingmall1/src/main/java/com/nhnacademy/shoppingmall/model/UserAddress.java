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
@Table(name = "user_address")
public class UserAddress {

    @EmbeddedId
    private Pk pk;

    @Column(name = "address_info")
    private String addressInfo;


    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    public static class Pk implements Serializable {

        @NotNull
        @Column(name = "address_id")
        private int addressId;

        @NotNull
        @Column(name = "user_id")
        private String userId;

    }
}
