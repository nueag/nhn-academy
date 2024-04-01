package com.nhnacademy.certification.service.impl;

import com.nhnacademy.certification.model.FamilyRelationship;
import com.nhnacademy.certification.model.Resident;
import com.nhnacademy.certification.model.request.FamilyRequest;
import com.nhnacademy.certification.repository.FamilyRelationshipRepository;
import com.nhnacademy.certification.repository.ResidentRepository;
import com.nhnacademy.certification.service.FamilyRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    @Autowired
    public FamilyRelationshipServiceImpl(FamilyRelationshipRepository familyRelationshipRepository,
                                         ResidentRepository residentRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.residentRepository = residentRepository;
    }

    public FamilyRelationship addFamilyRelationship(int baseResidentSerialNumber, FamilyRequest request) {
        Resident baseResident = residentRepository.findById(baseResidentSerialNumber)
                .orElseThrow(() -> new RuntimeException("Base resident not found"));

        FamilyRelationship familyRelationship = new FamilyRelationship();
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();
        pk.setBaseResidentSerialNumber(baseResidentSerialNumber);
        pk.setFamilyResidentSerialNumber(request.getFamilyResidentSerialNumber());

        familyRelationship.setPk(pk);
        familyRelationship.setFamilyRelationshipCode(request.getFamilyRelationshipCode());
        familyRelationship.setResident(baseResident);
        FamilyRelationship result = familyRelationshipRepository.save(familyRelationship);
        familyRelationshipRepository.flush();
        return result;
    }

    public FamilyRelationship updateFamilyRelationship(int baseResidentSerialNumber, int familySerialNumber,
                                                       FamilyRequest request) {
        request.setFamilyResidentSerialNumber(familySerialNumber);
        return addFamilyRelationship(baseResidentSerialNumber, request);
    }

    public void deleteFamilyRelationship(int baseResidentSerialNumber, int familySerialNumber) {
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(baseResidentSerialNumber, familySerialNumber);

        if (!familyRelationshipRepository.existsById(pk)) {
            throw new RuntimeException("Family relationship not found");
        }

        familyRelationshipRepository.deleteById(pk);
    }
}
