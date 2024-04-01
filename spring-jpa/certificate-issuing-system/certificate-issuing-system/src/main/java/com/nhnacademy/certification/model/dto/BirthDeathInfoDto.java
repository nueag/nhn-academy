package com.nhnacademy.certification.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public interface BirthDeathInfoDto {
    String getName();
    String getResidentRegistrationNumber();
    String getGenderCode();
    String getBirthDate();
    String getBirthPlaceCode();
    String getRegistrationBaseAddress();
    String getDeathDate();
    String getDeathPlaceCode();
    String getDeathPlaceAddress();

}
