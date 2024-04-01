package com.nhnacademy.certification.repository;

import com.nhnacademy.certification.config.RootConfig;
import com.nhnacademy.certification.config.WebConfig;
import com.nhnacademy.certification.model.Household;
import com.nhnacademy.certification.model.Resident;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class HouseholdRepositoryTest {
    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    @Test
    void saveHouseholdTest() {
        Resident resident = new Resident(100, "이가은", "123456-1234567", "여",
                "2001-09-27", "병원", "광주시 북구 신안동 419-1", null, null, null);
        residentRepository.save(resident);
        Household household = new Household(100, resident, null, "세대분리", "경기도 성남시 분당구 대왕판교로 645번길");
        Household testHousehold = householdRepository.save(household);

        Household findHousehold = householdRepository.findById(testHousehold.getHouseholdSerialNumber()).get();

        Assertions.assertThat(testHousehold.getHouseholdSerialNumber())
                .isEqualTo(findHousehold.getHouseholdSerialNumber());
    }

    @Test
    void updateHouseholdTest() {
        Resident resident = new Resident(100, "이가은", "123456-1234567", "여",
                "2001-09-27", "병원", "광주시 북구 신안동 419-1", null, null, null);
        residentRepository.save(resident);
        Household household = new Household(100, resident, null, "세대분리", "경기도 성남시 분당구 대왕판교로 645번길");
        Household testHousehold = householdRepository.save(household);
        Household household2 = new Household(100, resident, null, "메롱", "경기도 성남시 분당구 대왕판교로 645번길");
        Household updateHousehold = householdRepository.save(household2);

        Household findHousehold = householdRepository.findById(testHousehold.getHouseholdSerialNumber()).get();

        Assertions.assertThat(household.getHouseholdCompositionReasonCode())
                .isNotEqualTo(findHousehold.getHouseholdCompositionReasonCode());
        Assertions.assertThat(updateHousehold.getHouseholdCompositionReasonCode())
                .isEqualTo(findHousehold.getHouseholdCompositionReasonCode());
    }


}