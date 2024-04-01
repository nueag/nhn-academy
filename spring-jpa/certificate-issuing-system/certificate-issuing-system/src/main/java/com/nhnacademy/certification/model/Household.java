package com.nhnacademy.certification.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "household")
public class Household {
    @Id
    @NotNull
    @Column(name = "household_serial_number")
    private int householdSerialNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;

    @NotNull
    @Column(name = "household_composition_date")
    private String householdCompositionDate;

    @NotNull
    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode;

    @NotNull
    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;
}
