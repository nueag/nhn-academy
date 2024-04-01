package com.nhnacademy.certification.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resident")
public class Resident {
    @Id
    @NotNull
    @Column(name = "resident_serial_number")
    private int residentSerialNumber;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "resident_registration_number")
    private String residentRegistrationNumber;

    @NotNull
    @Column(name = "gender_code")
    private String genderCode;

    @NotNull
    @Column(name = "birth_date")
    private String birthDate;

    @NotNull
    @Column(name = "birth_place_code")
    private String birthPlaceCode;

    @NotNull
    @Column(name = "registration_base_address")
    private String registrationBaseAddress;

    @Column(name = "death_date")
    private String deathDate;

    @Column(name = "death_place_code")
    private String deathPlaceCode;

    @Column(name = "death_place_address")
    private String deathPlaceAddress;
//
//    @OneToMany(mappedBy = "resident", fetch = FetchType.EAGER)
//    List<FamilyRelationship> familyRelationshipList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "resident", fetch = FetchType.EAGER)
//    List<BirthDeathReportResident> birthDeathReportResidentList = new ArrayList<>();

    public Resident(int residentSerialNumber, String name, String residentRegistrationNumber, String genderCode,
                    String birthDate, String birthPlaceCode, String registrationBaseAddress,
                    String deathDate,
                    String deathPlaceCode, String deathPlaceAddress) {
        this.residentSerialNumber = residentSerialNumber;
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
        this.birthDate = birthDate;
        this.birthPlaceCode = birthPlaceCode;
        this.registrationBaseAddress = registrationBaseAddress;
        this.deathDate = deathDate;
        this.deathPlaceCode = deathPlaceCode;
        this.deathPlaceAddress = deathPlaceAddress;
    }
}
