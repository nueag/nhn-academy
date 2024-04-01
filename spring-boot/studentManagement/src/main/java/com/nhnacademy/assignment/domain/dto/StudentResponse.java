package com.nhnacademy.assignment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class StudentResponse {
    private long id;
    private String name;
    private String email;
    private int score;
    private String comment;

    public StudentResponse(String name, String email, int score, String comment) {
        this.name = name;
        this.email = email;
        this.score = score;
        this.comment = comment;
    }
}
