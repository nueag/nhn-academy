package com.nhnacademy.certification.repository;

import com.nhnacademy.certification.config.RootConfig;
import com.nhnacademy.certification.config.WebConfig;
import com.nhnacademy.certification.model.Resident;
import com.nhnacademy.certification.model.dto.BirthDeathInfoDto;
import com.nhnacademy.certification.model.dto.ComplainantDto;
import com.nhnacademy.certification.model.dto.CompositionInfoDto;
import com.nhnacademy.certification.model.dto.FamilyRelationshipDto;
import com.nhnacademy.certification.model.dto.ParentInfoDto;
import com.nhnacademy.certification.model.dto.PersonalInfoDto;
import com.nhnacademy.certification.model.dto.ResidentCertificateInfo;
import com.nhnacademy.certification.model.dto.ResidentInfo;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ResidentRepositoryTest {
    @Autowired
    private ResidentRepository residentRepository;

    @Test
    void saveResidentTest() {
        Resident resident = new Resident(100, "이가은", "123456-1234567", "여",
                "2001-09-27", "병원", "광주시 북구 신안동 419-1", null, null, null);
        Resident testResident = residentRepository.save(resident);

        Resident findHousehold = residentRepository.findById(testResident.getResidentSerialNumber()).get();

        Assertions.assertThat(testResident.getName()).isEqualTo(findHousehold.getName());
    }

    @Test
    void updateResidentTest() {
        Resident resident = new Resident(100, "이가은", "123456-1234567", "여",
                "2001-09-27", "병원", "광주시 북구 신안동 419-1", null, null, null);
        residentRepository.save(resident);
        Resident newResident = new Resident(100, "메롱", "123456-1234567", "여",
                "2001-09-27", "병원", "광주시 북구 신안동 419-1", null, null, null);
        Resident updateResident = residentRepository.save(newResident);
        residentRepository.flush();

        Resident findHousehold = residentRepository.findById(updateResident.getResidentSerialNumber()).get();

        Assertions.assertThat(resident.getName()).isNotEqualTo(findHousehold.getName());
        Assertions.assertThat(updateResident.getName()).isEqualTo(findHousehold.getName());
    }

    @Test
    void findAllByIdTest() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<ResidentInfo> residentIdList = residentRepository.findAllResidentInfoBy(pageable);

        Assertions.assertThat(residentIdList.getContent().size()).isEqualTo(3);
    }

    @Test
    void getResidentCertificateInfoTest() {
        List<ResidentCertificateInfo> residentCertificateInfo = residentRepository.getResidentCertificateInfo(4);

        Assertions.assertThat(residentCertificateInfo.size()).isEqualTo(3);
    }

    @Test
    void getCompositionCertificateInfoTest() {
        List<CompositionInfoDto> compositionCertificateInfo = residentRepository.getCompositionCertificateInfo(4);

        Assertions.assertThat(compositionCertificateInfo.size()).isEqualTo(4);
    }

    @Test
    void getFamilyRelationshipListTest() {
        List<FamilyRelationshipDto> familyRelationshipDto = residentRepository.getFamilyRelationshipList(4);
        Assertions.assertThat(familyRelationshipDto.size()).isEqualTo(4);
    }

    @Test
    void findFamilyByResidentSerialNumberTest() {
        PersonalInfoDto personalInfoDto = residentRepository.findFamilyByResidentSerialNumber(4);
        Assertions.assertThat(personalInfoDto.getName()).isEqualTo("남기준");
    }

    @Test
    void findBirthAndDeathByResidentSerialNumberTest() {
        BirthDeathInfoDto birthDeathInfoDto = residentRepository.findBirthAndDeathByResidentSerialNumber(4);
        Assertions.assertThat(birthDeathInfoDto.getName()).isEqualTo("남기준");
    }

    @Test
    void getParentInfoListTest() {
        List<ParentInfoDto> parentInfoDto = residentRepository.getParentInfoList(4);
        for (ParentInfoDto parentInfoDto1 : parentInfoDto) {
            System.out.println(parentInfoDto1);
        }
        Assertions.assertThat(parentInfoDto.get(0).getFamilyRelationshipCode()).isEqualTo("부");
    }

    @Test
    void getReporterTest() {
        ComplainantDto complainantDto = residentRepository.getReporter(4);
        System.out.println(complainantDto);
        Assertions.assertThat(complainantDto.getName()).isEqualTo("남기석");
    }
}