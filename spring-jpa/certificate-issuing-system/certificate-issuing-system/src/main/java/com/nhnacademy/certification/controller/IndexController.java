package com.nhnacademy.certification.controller;

import com.nhnacademy.certification.model.dto.ResidentCertificateDto;
import com.nhnacademy.certification.model.dto.ResidentInfo;
import com.nhnacademy.certification.service.ResidentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private ResidentService residentService;

    @Autowired
    public IndexController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Pageable pageable = PageRequest.of(0, 3);
        Page<ResidentInfo> residentInfoPage = residentService.findAllResidentInfoBy(pageable);
        List<ResidentCertificateDto> documentList = residentService.residentDtoList(pageable);
        model.addAttribute("documentList", documentList);
        model.addAttribute("currentPage", 0);
        model.addAttribute("totalPages", residentInfoPage.getTotalPages());
        return "thymeleaf/main/index";
    }

    @GetMapping("/index/{page}")
    public String index(@PathVariable("page") int page,
                        Model model) {
        Pageable pageable = PageRequest.of(page - 1, 3);
        Page<ResidentInfo> residentInfoPage = residentService.findAllResidentInfoBy(pageable);
        List<ResidentCertificateDto> documentList = residentService.residentDtoList(pageable);
        model.addAttribute("documentList", documentList);
        model.addAttribute("currentPage", page - 1);
        model.addAttribute("totalPages", residentInfoPage.getTotalPages());
        return "thymeleaf/main/index";
    }

}
