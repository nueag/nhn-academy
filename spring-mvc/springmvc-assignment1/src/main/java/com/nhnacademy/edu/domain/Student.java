package com.nhnacademy.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Student {
    private long id;
    @Setter
    private String name;
    @Setter
    private String email;
    @Setter
    private int score;
    @Setter
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

    public static Student create(long id, String name, String email) {
        return new Student(id, name, email);
    }
    private static final String MASK = "***";
    public static Student notScoreAndComment(Student student) {
        Student newStudent = Student.create(student.getId(), student.getName(), student.getEmail());
        newStudent.setScore(0);
        newStudent.setComment(MASK);
        return newStudent;
    }
}
