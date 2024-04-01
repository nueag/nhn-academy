package com.nhnacademy.certification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public interface PersonalInfoDto {
    String getName();
    String getRegistrationBaseAddress();

    String getBirthDate();
    String residentRegistrationNumber();
    String getGenderCode();
}
