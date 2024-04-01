package com.nhnacademy.edu.repository;


import com.nhnacademy.edu.domain.User;

public interface UserRepository {
    boolean exists(String id);

    boolean matches(String id, String password);

    User getUser(String id);

    User addUser(String id, String password, int age, String name);


    User addUser(String id, String password);
}
