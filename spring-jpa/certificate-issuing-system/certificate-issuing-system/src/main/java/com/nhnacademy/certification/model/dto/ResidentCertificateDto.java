package com.nhnacademy.certification.model.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class ResidentCertificateDto {
    private int residentSerialNumber;
    private String name;
    private String residenceCertificate = "";
    private String familyRelationCertificate = "";
    private String birthCertificate = "";
    private String deathCertificate = "";

    public ResidentCertificateDto(int residentSerialNumber, String name, List<String> categoryList) {
        this.residentSerialNumber = residentSerialNumber;
        this.name = name;
        for(String category : categoryList) {
            categoryCheck(category);
        }
    }

    public void categoryCheck(String category) {
        switch (category) {
            case "주민등록등본":
                this.residenceCertificate = category;
                break;
            case "가족관계증명서":
                this.familyRelationCertificate = category;
                break;
            case "출생신고서":
                this.birthCertificate = category;
                break;
            case "사망신고서":
                this.deathCertificate = category;
                break;
            default:
                break;
        }
    }
}
