package com.nhnacademy.certification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class ParentInfoDto {
    private String familyRelationshipCode;
    private String name;
    private String residentRegistrationNumber;

}
