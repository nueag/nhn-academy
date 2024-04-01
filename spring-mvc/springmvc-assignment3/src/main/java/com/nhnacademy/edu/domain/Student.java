package com.nhnacademy.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class Student {
    private long id;
    private String name;
    private String email;
    private int score;
    private String comment;

    public Student() {

    }

    public Student(long id) {
        this.id = id;
    }

    public Student(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    private static final String MASK = "***";

}
