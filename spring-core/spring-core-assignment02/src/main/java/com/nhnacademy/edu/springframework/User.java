package com.nhnacademy.edu.springframework;

import lombok.Getter;

@Getter
public class User {
    private final String name;

    public User(String name) {
        this.name = name;
    }

}
