package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.User;
import com.nhnacademy.shoppingmall.model.UserAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, String> {
    int countByUserId(String userId);

    List<UserAddress> findByUserId(String userId);

    int deleteByAddressId(int addressId);

}
