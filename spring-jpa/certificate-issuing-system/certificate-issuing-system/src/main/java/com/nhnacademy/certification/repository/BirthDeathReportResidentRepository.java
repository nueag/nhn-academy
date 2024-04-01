package com.nhnacademy.certification.repository;

import com.nhnacademy.certification.model.BirthDeathReportResident;
import com.nhnacademy.certification.model.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk > {
    boolean existsById(BirthDeathReportResident.Pk id);

    void deleteById(BirthDeathReportResident.Pk id);
}
