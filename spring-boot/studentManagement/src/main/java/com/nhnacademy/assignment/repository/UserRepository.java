package com.nhnacademy.assignment.repository;


import com.nhnacademy.assignment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select password from User where id = ?1")
    String getPassword(String id);
}
