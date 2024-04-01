package com.nhnacademy.assignment.service;

import com.nhnacademy.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean matches(String id, String pwd) {
        String password = userRepository.getPassword(id);
        if (password.equals(pwd)) {
            return true;
        }
        return false;
    }
}
