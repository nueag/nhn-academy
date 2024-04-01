package com.nhnacademy.certification.controller;

import com.nhnacademy.certification.model.dto.DeathCertificateDto;
import com.nhnacademy.certification.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteController {
    private ResidentService residentService;

    @Autowired
    public DeleteController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping("/deleteDocument/{residentSerialNumber}")
    public String delete(@PathVariable("residentSerialNumber") int residentSerialNumber) {
        residentService.deleteResident(residentSerialNumber);
        return "redirect:/";
    }
}
