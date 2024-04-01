package com.nhnacademy.certification.controller;

import com.nhnacademy.certification.service.ResidentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DocumentListController {
    private ResidentService residentService;

    @Autowired
    public DocumentListController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/issueList/{residentSerialNumber}")
    public String delete(@PathVariable("residentSerialNumber") int residentSerialNumber,
                         Model model) {
        List<String> documentList = residentService.findAllByResidentSerialNumber(residentSerialNumber);
        model.addAttribute("documentList", documentList);
        return "thymeleaf/certification/certificateList";
    }
}
