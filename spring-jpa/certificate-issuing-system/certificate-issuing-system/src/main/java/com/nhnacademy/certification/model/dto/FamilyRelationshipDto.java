package com.nhnacademy.certification.model.dto;

import com.querydsl.core.types.dsl.StringPath;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FamilyRelationshipDto {
    private String familyRelationshipCode;
    private String name;
    private String birthDate;
    private String residentRegistrationNumber;
    private String genderCode;

    public FamilyRelationshipDto(String familyRelationshipCode, String name, String birthDate,
                                 String residentRegistrationNumber, String genderCode) {
        this.familyRelationshipCode = familyRelationshipCode;
        this.name = name;
        this.birthDate = birthDate;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
    }

    public FamilyRelationshipDto(StringPath familyRelationshipCode, StringPath name, StringPath birthDate, StringPath residentRegistrationNumber, StringPath genderCode) {
        this.familyRelationshipCode = String.valueOf(familyRelationshipCode);
        this.name = String.valueOf(name);
        this.birthDate = String.valueOf(birthDate);
        this.residentRegistrationNumber = String.valueOf(residentRegistrationNumber);
        this.genderCode = String.valueOf(genderCode);
    }

    public FamilyRelationshipDto(String name, String birthDate, String residentRegistrationNumber, String genderCode) {
        this.name = name;
        this.birthDate = birthDate;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
    }

    @Override
    public String toString() {
        return "FamilyRelationshipDto{" +
                "familyRelationshipCode='" + familyRelationshipCode + '\'' +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", residentRegistrationNumber='" + residentRegistrationNumber + '\'' +
                ", genderCode='" + genderCode + '\'' +
                '}';
    }

    public String getResidentRegistrationNumber() {
        return residentRegistrationNumber;
    }

    public String getGenderCode() {
        return genderCode;
    }
}
