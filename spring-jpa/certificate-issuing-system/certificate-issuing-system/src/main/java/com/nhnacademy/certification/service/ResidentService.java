package com.nhnacademy.certification.service;

import com.nhnacademy.certification.model.Resident;
import com.nhnacademy.certification.model.dto.BirthCertificateDto;
import com.nhnacademy.certification.model.dto.DeathCertificateDto;
import com.nhnacademy.certification.model.dto.FamilyRelationshipCertificateDto;
import com.nhnacademy.certification.model.dto.ResidentCertificateDto;
import com.nhnacademy.certification.model.dto.ResidentInfo;
import com.nhnacademy.certification.model.dto.ResidentRegistrationCertificateDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentService {
    Resident getResidentById(int residentSerialNumber);

    Resident registerResident(Resident resident);

    Resident updateResident(Resident resident);

    List<ResidentCertificateDto> residentDtoList(Pageable pageable);

    Page<ResidentInfo> findAllResidentInfoBy(Pageable pageable);

    ResidentRegistrationCertificateDto getResidentRegistrationCertificate(int residentSerialNumber);

    FamilyRelationshipCertificateDto getFamilyRelationshipCertificate(int residentSerialNumber);

    BirthCertificateDto getBirthCertificate(int residentSerialNumber);

    DeathCertificateDto getDeathCertificate(int residentSerialNumber);

    void deleteResident(int residentSerialNumber);

    List<String> findAllByResidentSerialNumber(int residentSerialNumber);
}
