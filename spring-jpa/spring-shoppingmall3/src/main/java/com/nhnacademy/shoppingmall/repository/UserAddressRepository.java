package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.AddressOnly;
import com.nhnacademy.shoppingmall.model.User;
import com.nhnacademy.shoppingmall.model.UserAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, String> {
    int countByAddressId(int addressId);

    List<AddressOnly> findByUser_UserId(String userId);

    int deleteByAddressId(int addressId);

}
