package com.nhnacademy.certification.service;

import com.nhnacademy.certification.model.FamilyRelationship;
import com.nhnacademy.certification.model.request.FamilyRequest;

public interface FamilyRelationshipService {

    FamilyRelationship addFamilyRelationship(int baseResidentSerialNumber, FamilyRequest request);

    FamilyRelationship updateFamilyRelationship(int baseResidentSerialNumber, int familySerialNumber,
                                                FamilyRequest request);

    void deleteFamilyRelationship(int baseResidentSerialNumber, int familySerialNumber);
}
