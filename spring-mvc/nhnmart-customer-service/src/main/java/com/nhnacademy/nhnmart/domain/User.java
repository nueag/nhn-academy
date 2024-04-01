package com.nhnacademy.nhnmart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String password;
    private String name;
    private boolean isAdmin;

}
