package com.nhnacademy.certification.controller;

import com.nhnacademy.certification.model.dto.ResidentRegistrationCertificateDto;
import com.nhnacademy.certification.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ResidenceCertificateController {
    private ResidentService residentService;

    @Autowired
    public ResidenceCertificateController(ResidentService residentService) {
        this.residentService = residentService;
    }


    @GetMapping("/residenceCertificate/{id}")
    public String index(@PathVariable("id") int residentSerialNumber,
                        Model model) {
        ResidentRegistrationCertificateDto residentRegistrationCertificate =
                residentService.getResidentRegistrationCertificate(residentSerialNumber);
        model.addAttribute("residentCertificate", residentRegistrationCertificate);
        return "thymeleaf/certification/residenceCertificate";
    }
}