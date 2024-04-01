package com.nhnacademy.certification.service.impl;

import com.nhnacademy.certification.model.CertificateIssue;
import com.nhnacademy.certification.model.Resident;
import com.nhnacademy.certification.model.dto.BirthCertificateDto;
import com.nhnacademy.certification.model.dto.BirthDeathInfoDto;
import com.nhnacademy.certification.model.dto.ComplainantDto;
import com.nhnacademy.certification.model.dto.CompositionInfoDto;
import com.nhnacademy.certification.model.dto.DeathCertificateDto;
import com.nhnacademy.certification.model.dto.FamilyRelationshipCertificateDto;
import com.nhnacademy.certification.model.dto.FamilyRelationshipDto;
import com.nhnacademy.certification.model.dto.OnlyAddress;
import com.nhnacademy.certification.model.dto.ParentInfoDto;
import com.nhnacademy.certification.model.dto.PersonalInfoDto;
import com.nhnacademy.certification.model.dto.ResidentCertificateDto;
import com.nhnacademy.certification.model.dto.ResidentCertificateInfo;
import com.nhnacademy.certification.model.dto.ResidentInfo;
import com.nhnacademy.certification.model.dto.ResidentRegistrationCertificateDto;
import com.nhnacademy.certification.repository.CertificateIssueRepository;
import com.nhnacademy.certification.repository.ResidentRepository;
import com.nhnacademy.certification.service.ResidentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("residentService")
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final CertificateIssueRepository certificateIssueRepository;

    public ResidentServiceImpl(ResidentRepository residentRepository,
                               CertificateIssueRepository certificateIssueRepository) {
        this.residentRepository = residentRepository;
        this.certificateIssueRepository = certificateIssueRepository;
    }


    @Override
    public Resident getResidentById(int residentSerialNumber) {
        return residentRepository.findById(residentSerialNumber).get();
    }

    @Override
    public Resident registerResident(Resident resident) {
        return residentRepository.save(resident);
    }

    @Override
    public Resident updateResident(Resident resident) {
        return residentRepository.save(resident);
    }

    @Override
    public List<ResidentCertificateDto> residentDtoList(Pageable pageable) {
        Page<ResidentInfo> residentIdList = residentRepository.findAllResidentInfoBy(pageable);
        List<ResidentCertificateDto> dtoList = new ArrayList<>();
        for (ResidentInfo residentInfo : residentIdList) {
            List<String> categoryList =
                    certificateIssueRepository.findAllByResidentSerialNumber(residentInfo.getResidentSerialNumber());
            dtoList.add(new ResidentCertificateDto(residentInfo.getResidentSerialNumber(), residentInfo.getName(),
                    categoryList));
        }
        return dtoList;
    }

    @Override
    public Page<ResidentInfo> findAllResidentInfoBy(Pageable pageable) {
        return residentRepository.findAllResidentInfoBy(pageable);
    }

    @Override
    public ResidentRegistrationCertificateDto getResidentRegistrationCertificate(int residentSerialNumber) {
        CertificateIssue certificateIssue =
                certificateIssueRepository.findAllByResidentSerialNumberAndCertificateTypeCode(residentSerialNumber,
                        "주민등록등본");
        CompositionInfoDto oneCompositionInfo = residentRepository.getOneCompositionInfo(residentSerialNumber);
        List<ResidentCertificateInfo> addressInfo = residentRepository.getResidentCertificateInfo(residentSerialNumber);
        List<CompositionInfoDto> compositionCertificateInfo =
                residentRepository.getCompositionCertificateInfo(residentSerialNumber);
        ResidentRegistrationCertificateDto residentCertificateDto = new ResidentRegistrationCertificateDto(
                certificateIssue.getCertificateIssueDate(), certificateIssue.getCertificateConfirmationNumber(),
                oneCompositionInfo.getName(), oneCompositionInfo.getHouseholdCompositionChangeReasonCode(),
                oneCompositionInfo.getReportDate(), addressInfo, compositionCertificateInfo);
        return residentCertificateDto;
    }

    @Override
    public FamilyRelationshipCertificateDto getFamilyRelationshipCertificate(int residentSerialNumber) {
        CertificateIssue certificateIssue =
                certificateIssueRepository.findAllByResidentSerialNumberAndCertificateTypeCode(residentSerialNumber,
                        "가족관계증명서");
        PersonalInfoDto personalInfoDto = residentRepository.findFamilyByResidentSerialNumber(residentSerialNumber);
        List<FamilyRelationshipDto> familyRelationshipDtoList =
                residentRepository.getFamilyRelationshipList(residentSerialNumber);

        return new FamilyRelationshipCertificateDto(certificateIssue.getCertificateIssueDate(),
                certificateIssue.getCertificateConfirmationNumber(), personalInfoDto, familyRelationshipDtoList);
    }

    @Override
    public BirthCertificateDto getBirthCertificate(int residentSerialNumber) {
        CertificateIssue certificateIssue =
                certificateIssueRepository.findAllByResidentSerialNumberAndCertificateTypeCode(residentSerialNumber,
                        "출생신고서");
        BirthDeathInfoDto birthCertificateDto =
                residentRepository.findBirthAndDeathByResidentSerialNumber(residentSerialNumber);
        List<ParentInfoDto> parentInfoDtoList = residentRepository.getParentInfoList(residentSerialNumber);
        ComplainantDto complainantDto = residentRepository.getReporter(residentSerialNumber);
        return new BirthCertificateDto(certificateIssue.getCertificateIssueDate(),
                certificateIssue.getCertificateConfirmationNumber(), birthCertificateDto, parentInfoDtoList,
                complainantDto);
    }

    @Override
    public DeathCertificateDto getDeathCertificate(int residentSerialNumber) {
        CertificateIssue certificateIssue =
                certificateIssueRepository.findAllByResidentSerialNumberAndCertificateTypeCode(residentSerialNumber,
                        "사망신고서");
        OnlyAddress onlyAddress = residentRepository.findAddressByResidentSerialNumber(residentSerialNumber);
        BirthDeathInfoDto birthDeathInfoDto =
                residentRepository.findBirthAndDeathByResidentSerialNumber(residentSerialNumber);
        ComplainantDto complainantDto = residentRepository.getReporter(residentSerialNumber);
        return new DeathCertificateDto(certificateIssue.getCertificateIssueDate(),
                onlyAddress.getRegistrationBaseAddress(), birthDeathInfoDto, complainantDto);
    }

    @Override
    public void deleteResident(int residentSerialNumber) {
        if (!residentRepository.existsById(residentSerialNumber)) {
            throw new RuntimeException("resident not found");
        }
        residentRepository.deleteById(residentSerialNumber);
    }

    public List<String> findAllByResidentSerialNumber(int residentSerialNumber) {
        return certificateIssueRepository.findAllByResidentSerialNumber(residentSerialNumber);
    }
}
