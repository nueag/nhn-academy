package com.nhnacademy.certification.model.dto;

import com.nhnacademy.certification.model.CertificateIssue;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyRelationshipCertificateDto {
    private String certificateIssueDate;
    private long certificateConfirmationNumber;
    private PersonalInfoDto personalInfoDto;
    List<FamilyRelationshipDto> familyRelationshipList = new ArrayList<>();

    public List<FamilyRelationshipDto> getFamilyRelationshipList() {
        return familyRelationshipList;
    }
}
