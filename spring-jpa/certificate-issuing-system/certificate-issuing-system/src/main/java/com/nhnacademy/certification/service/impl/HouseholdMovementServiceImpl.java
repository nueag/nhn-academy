package com.nhnacademy.certification.service.impl;

import com.nhnacademy.certification.model.Household;
import com.nhnacademy.certification.model.HouseholdMovementAddress;
import com.nhnacademy.certification.model.request.HouseholdMovementRequest;
import com.nhnacademy.certification.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.certification.repository.HouseholdRepository;
import com.nhnacademy.certification.service.HouseholdMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseholdMovementServiceImpl implements HouseholdMovementService {
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseholdRepository householdRepository;

    @Autowired
    public HouseholdMovementServiceImpl(HouseholdMovementAddressRepository householdMovementAddressRepository,
                                        HouseholdRepository householdRepository) {
        this.householdMovementAddressRepository = householdMovementAddressRepository;
        this.householdRepository = householdRepository;
    }

    @Override
    public HouseholdMovementAddress registerHouseholdMovement(int householdSerialNumber,
                                                              HouseholdMovementRequest request) {
        HouseholdMovementAddress.Pk pk =
                new HouseholdMovementAddress.Pk(householdSerialNumber, request.getHouseMovementReportDate());
        Household household = householdRepository.findById(householdSerialNumber).get();
        HouseholdMovementAddress householdMovementAddress =
                new HouseholdMovementAddress(pk, request.getHouseMovementAddress(), request.getLastAddressYn(),
                        household);
        return householdMovementAddressRepository.save(householdMovementAddress);
    }

    @Override
    public HouseholdMovementAddress updateHouseholdMovement(int householdSerialNumber, String reportDate,
                                                            HouseholdMovementRequest request) {
        request.setHouseMovementReportDate(reportDate);
        return registerHouseholdMovement(householdSerialNumber, request);
    }

    @Override
    public void deleteHouseholdMovement(int householdSerialNumber, String reportDate) {
        if (!householdMovementAddressRepository.existsById(
                new HouseholdMovementAddress.Pk(householdSerialNumber, reportDate))) {
            throw new RuntimeException("householdMovementAddress not found");
        }
        householdMovementAddressRepository.deleteById(
                new HouseholdMovementAddress.Pk(householdSerialNumber, reportDate));

    }
}
