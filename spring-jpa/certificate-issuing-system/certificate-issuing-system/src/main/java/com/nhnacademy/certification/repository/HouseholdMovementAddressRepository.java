package com.nhnacademy.certification.repository;

import com.nhnacademy.certification.model.FamilyRelationship;
import com.nhnacademy.certification.model.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {
    boolean existsById(HouseholdMovementAddress.Pk id);

    void deleteById(HouseholdMovementAddress.Pk id);
}
