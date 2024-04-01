package com.nhnacademy.shoppingmall.model;

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
@Table(name = "users")
public class User {
    @Id
    @NotNull
    @Column(name = "user_id")
    private String userId;

    @NotNull
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @Column(name = "user_password")
    private String userPassword;

    @NotNull
    @Column(name = "user_birth")
    private String userBirth;

    @NotNull
    @Column(name = "user_auth")
    private String userAuth;

    @NotNull
    @Column(name = "user_point")
    private int userPoint;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "latest_login_at")
    private LocalDateTime latestLoginAt;
}
