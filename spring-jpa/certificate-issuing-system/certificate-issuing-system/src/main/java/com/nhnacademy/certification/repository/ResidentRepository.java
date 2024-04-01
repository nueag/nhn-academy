package com.nhnacademy.certification.repository;

import com.nhnacademy.certification.model.Resident;
import com.nhnacademy.certification.model.dto.BirthDeathInfoDto;
import com.nhnacademy.certification.model.dto.ComplainantDto;
import com.nhnacademy.certification.model.dto.CompositionInfoDto;
import com.nhnacademy.certification.model.dto.OnlyAddress;
import com.nhnacademy.certification.model.dto.OnlyName;
import com.nhnacademy.certification.model.dto.PersonalInfoDto;
import com.nhnacademy.certification.model.dto.ResidentCertificateInfo;
import com.nhnacademy.certification.model.dto.ResidentInfo;
import com.nhnacademy.certification.repository.custom.ResidentRepositoryCustom;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResidentRepository extends ResidentRepositoryCustom, JpaRepository<Resident, Integer> {

    Page<ResidentInfo> findAllResidentInfoBy(Pageable pageable);

    boolean existsById(int residentSerialNumber);

    void deleteById(int residentSerialNumber);

    OnlyName findByResidentSerialNumber(int residentSerialNumber);

    OnlyAddress findAddressByResidentSerialNumber(int residentSerialNumber);

    @Query("select new com.nhnacademy.certification.model.dto.ResidentCertificateInfo(r.name, hm.houseMovementAddress, hm.pk.houseMovementReportDate) from Resident r inner join Household h on r.residentSerialNumber = h.resident.residentSerialNumber left join HouseholdMovementAddress hm on h.householdSerialNumber = hm.household.householdSerialNumber where r.residentSerialNumber=?1")
    List<ResidentCertificateInfo> getResidentCertificateInfo(int residentSerialNumber);

    @Query("select new com.nhnacademy.certification.model.dto.CompositionInfoDto(hc.householdRelationshipCode, r.name, r.residentRegistrationNumber, hc.reportDate, hc.householdCompositionChangeReasonCode) from HouseholdCompositionResident hc inner join Resident r on r.residentSerialNumber = hc.resident.residentSerialNumber where hc.household.householdSerialNumber in (select householdSerialNumber from Household where resident.residentSerialNumber = ?1)")
    List<CompositionInfoDto> getCompositionCertificateInfo(int residentSerialNumber);

    @Query("select new com.nhnacademy.certification.model.dto.CompositionInfoDto(hc.householdRelationshipCode, r.name, r.residentRegistrationNumber, hc.reportDate, hc.householdCompositionChangeReasonCode) from Resident r inner join HouseholdCompositionResident hc on r.residentSerialNumber = hc.resident.residentSerialNumber where r.residentSerialNumber = ?1")
    CompositionInfoDto getOneCompositionInfo(int residentSerialNumber);

    PersonalInfoDto findFamilyByResidentSerialNumber(int residentSerialNumber);

    BirthDeathInfoDto findBirthAndDeathByResidentSerialNumber(int residentSerialNumber);


    @Query("select new com.nhnacademy.certification.model.dto.ComplainantDto(r.name, r.residentRegistrationNumber, b.birthReportQualificationsCode, b.deathReportQualificationsCode, b.emailAddress, b.phoneNumber) from BirthDeathReportResident b inner join Resident r on r.residentSerialNumber = b.pk.reportResidentSerialNumber where b.pk.residentSerialNumber = ?1")
    ComplainantDto getReporter(int residentSerialNumber);

}
