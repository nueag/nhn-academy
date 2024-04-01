package com.nhnacademy.certification.controller.rest;

import com.nhnacademy.certification.model.Resident;
import com.nhnacademy.certification.model.request.ResidentRequest;
import com.nhnacademy.certification.repository.ResidentRepository;
import com.nhnacademy.certification.service.ResidentService;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class ResidentController {
    private ResidentService residentService;

    @Autowired
    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping(value = "/residents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resident> registerJsonStudent(@RequestBody ResidentRequest residentRequest) {

        Resident saveResident = new Resident(residentRequest.getResidentSerialNumber(), residentRequest.getName(), residentRequest.getResidentRegistrationNumber(), residentRequest.getGenderCode(), residentRequest.getBirthDate(), residentRequest.getBirthPlaceCode(), residentRequest.getRegistrationBaseAddress(), residentRequest.getDeathDate(), residentRequest.getDeathPlaceCode(), residentRequest.getDeathPlaceAddress());
        Resident registerResident =
                residentService.registerResident(saveResident);
        return ResponseEntity.ok()
                .body(registerResident);
    }


    @PutMapping(value = "/residents/{serialNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resident> updateStudent(@PathVariable("serialNumber") int serialNumber,
                                                 @RequestBody ResidentRequest residentRequest) {
        Resident saveResident = new Resident(residentRequest.getResidentSerialNumber(), residentRequest.getName(), residentRequest.getResidentRegistrationNumber(), residentRequest.getGenderCode(), residentRequest.getBirthDate(), residentRequest.getBirthPlaceCode(), residentRequest.getRegistrationBaseAddress(), residentRequest.getDeathDate(), residentRequest.getDeathPlaceCode(), residentRequest.getDeathPlaceAddress());
        Resident registerResident = residentService.getResidentById(serialNumber);

        if (registerResident != null) {
            Resident updateResident = residentService.updateResident(saveResident);
            return ResponseEntity.ok()
                    .body(updateResident);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

