package com.nhnacademy.certification.repository;

import com.nhnacademy.certification.model.FamilyRelationship;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {

    boolean existsById(FamilyRelationship.Pk id);

    void deleteById(FamilyRelationship.Pk id);
}
