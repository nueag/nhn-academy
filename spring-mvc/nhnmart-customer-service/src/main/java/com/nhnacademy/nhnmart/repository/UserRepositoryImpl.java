package com.nhnacademy.nhnmart.repository;


import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.exception.UserAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public boolean exists(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public boolean matches(String id, String password) {
        System.out.println("id: " + id + " pwd: " + password);
        return Optional.ofNullable(getUser(id))
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public User getUser(String id) {
        return exists(id) ? userMap.get(id) : null;
    }


    @Override
    public User addCustomer(String id, String password, String name) {
        if (exists(id)) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(id, password, name, false);
        userMap.put(id, user);
        return user;
    }

    @Override
    public User addAdmin(String id, String password, String name) {
        if (exists(id)) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(id, password, name, true);
        userMap.put(id, user);
        return user;
    }


}
