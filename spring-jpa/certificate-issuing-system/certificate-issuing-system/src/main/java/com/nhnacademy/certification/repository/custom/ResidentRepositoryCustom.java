package com.nhnacademy.certification.repository.custom;

import com.nhnacademy.certification.model.dto.ComplainantDto;
import com.nhnacademy.certification.model.dto.FamilyRelationshipDto;
import com.nhnacademy.certification.model.dto.ParentInfoDto;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ResidentRepositoryCustom {
    List<FamilyRelationshipDto> getFamilyRelationshipList(int residentSerialNumber);
    List<ParentInfoDto> getParentInfoList(int residentSerialNumber);
}

