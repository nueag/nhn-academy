package com.nhnacademy.edu.repository;

import com.nhnacademy.edu.domain.Student;
import com.nhnacademy.edu.exception.StudentNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StudentRepositoryImpl implements StudentRepository {
    private final Map<Long, Student> studentMap = new HashMap<>();

    @Override
    public boolean exists(long id) {
        return studentMap.containsKey(id);
    }

    @Override
    public Student register(String name, String email, int score, String comment) {
        long id = getNextId();
        Student student = new Student(id, name, email, score, comment);
        studentMap.put(id, student);
        return student;
    }
    private long getNextId() {
        return studentMap.size() + 1;
    }
    @Override
    public Student getStudent(long id) {
        return exists(id) ? studentMap.get(id) : null;
    }

    @Override
    public void modify(Student student) {
        Student dbStudent = getStudent(student.getId());
        if(Objects.isNull(dbStudent)) {
            throw new StudentNotFoundException();
        }
        studentMap.remove(student.getId());
        studentMap.put(student.getId(), student);
    }
}
