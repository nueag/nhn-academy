package com.nhnacademy.certification.service.impl;

import com.nhnacademy.certification.model.Household;
import com.nhnacademy.certification.model.Resident;
import com.nhnacademy.certification.model.request.HouseholdRequest;
import com.nhnacademy.certification.repository.HouseholdRepository;
import com.nhnacademy.certification.repository.ResidentRepository;
import com.nhnacademy.certification.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseholdServiceImpl implements HouseholdService {
    private final ResidentRepository residentRepository;
    private final HouseholdRepository householdRepository;

    @Autowired
    public HouseholdServiceImpl(ResidentRepository residentRepository, HouseholdRepository householdRepository) {
        this.residentRepository = residentRepository;
        this.householdRepository = householdRepository;
    }

    @Override
    public Household registerHousehold(HouseholdRequest request) {
        Resident resident = residentRepository.findById(request.getHouseholdResidentSerialNumber()).get();
        Household household =
                new Household(request.getHouseholdSerialNumber(), resident, request.getHouseholdCompositionDate(),
                        request.getHouseholdCompositionReasonCode(), request.getCurrentHouseMovementAddress());
        return householdRepository.save(household);
    }

    @Override
    public void deleteHousehold(int householdSerialNumber) {
        if (!householdRepository.existsById(householdSerialNumber)) {
            throw new RuntimeException("Household not found");
        }
        householdRepository.deleteById(householdSerialNumber);
    }
}
