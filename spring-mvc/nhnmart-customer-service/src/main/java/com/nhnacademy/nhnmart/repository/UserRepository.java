package com.nhnacademy.nhnmart.repository;


import com.nhnacademy.nhnmart.domain.User;

public interface UserRepository {
    boolean exists(String id);

    boolean matches(String id, String password);

    User getUser(String id);

    User addCustomer(String id, String password, String name);

    User addAdmin(String id, String password, String name);

}
