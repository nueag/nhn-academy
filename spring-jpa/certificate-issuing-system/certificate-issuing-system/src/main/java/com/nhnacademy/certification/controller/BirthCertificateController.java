package com.nhnacademy.certification.controller;

import com.nhnacademy.certification.model.dto.BirthCertificateDto;
import com.nhnacademy.certification.model.dto.FamilyRelationshipCertificateDto;
import com.nhnacademy.certification.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BirthCertificateController {
    private ResidentService residentService;

    @Autowired
    public BirthCertificateController(ResidentService residentService) {
        this.residentService = residentService;
    }


    @GetMapping("/birthCertificate/{id}")
    public String index(@PathVariable("id") int residentSerialNumber,
                        Model model) {
        BirthCertificateDto birthCertificate = residentService.getBirthCertificate(residentSerialNumber);
        model.addAttribute("birthCertificate", birthCertificate);
        return "thymeleaf/certification/birthCertificate";
    }


}
