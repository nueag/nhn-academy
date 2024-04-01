package com.nhnacademy.certification.controller;

import com.nhnacademy.certification.model.dto.BirthCertificateDto;
import com.nhnacademy.certification.model.dto.DeathCertificateDto;
import com.nhnacademy.certification.model.dto.FamilyRelationshipCertificateDto;
import com.nhnacademy.certification.model.dto.OnlyAddress;
import com.nhnacademy.certification.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeathCertificateController {
    private ResidentService residentService;

    @Autowired
    public DeathCertificateController(ResidentService residentService) {
        this.residentService = residentService;
    }


    @GetMapping("/deathCertificate/{id}")
    public String index(@PathVariable("id") int residentSerialNumber,
                        Model model) {
        DeathCertificateDto deathCertificate =
                residentService.getDeathCertificate(residentSerialNumber);
        model.addAttribute("deathCertificate", deathCertificate);
        return "thymeleaf/certification/deathCertificate";
    }
}
