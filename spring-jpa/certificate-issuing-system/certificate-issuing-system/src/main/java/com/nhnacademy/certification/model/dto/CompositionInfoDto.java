package com.nhnacademy.certification.model.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompositionInfoDto {
    String householdRelationshipCode = "";
    String name = "";
    String residentRegistrationNumber = "";

    String reportDate;

    String householdCompositionChangeReasonCode = "";


    public CompositionInfoDto(String householdRelationshipCode, String name, String residentRegistrationNumber,
                              String reportDate, String householdCompositionChangeReasonCode) {
        this.householdRelationshipCode = householdRelationshipCode;
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.reportDate = reportDate;
        this.householdCompositionChangeReasonCode = householdCompositionChangeReasonCode;
    }
}
