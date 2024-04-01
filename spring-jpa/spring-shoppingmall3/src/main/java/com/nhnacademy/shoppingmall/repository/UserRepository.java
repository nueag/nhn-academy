package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByUserId(String userId);
    User save(User user);
    void deleteByUserId(String userId);
    int countByUserId(String userId);
    User findByUserId(String userId);
}
