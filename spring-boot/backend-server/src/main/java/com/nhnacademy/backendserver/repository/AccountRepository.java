package com.nhnacademy.backendserver.repository;


import com.nhnacademy.backendserver.service.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
