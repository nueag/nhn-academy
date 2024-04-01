package com.nhnacademy.certification.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BirthCertificateDto {
    private String certificateIssueDate;
    private long certificateConfirmationNumber;
    private BirthDeathInfoDto birthDeathInfoDto;
    private List<ParentInfoDto> parentInfoDtoList;
    private ComplainantDto complainantDto;

}
