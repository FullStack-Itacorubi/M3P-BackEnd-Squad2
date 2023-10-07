package com.labmedical.backend.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;

@Entity
@Table(name = "medications")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Medication Name is required")
    @Size(min = 5, max = 100, message = "Medication Name must be between 5 and 100 characters")
    @Column(name = "medication_name")
    private String medicationName;

    @NotNull(message = "Date is required")
    @Column(name = "date")
    private LocalDate date;

    @NotNull(message = "Time is required")
    @Column(name = "time")
    private LocalTime time;

    @NotNull(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private MedicationType type;

    @NotNull(message = "Quantity is required")
    @Digits(integer = 5, fraction = 2, message = "Quantity must have at least 2 decimal places")
    private BigDecimal quantity;

    @NotNull(message = "Unit is required")
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @NotBlank(message = "Observations are required")
    @Size(min = 10, max = 1000, message = "Observations must be between 10 and 1000 characters")
    private String observations;

    @NotNull(message = "System Status is required")
    @Column(name = "system_status")
    private Boolean systemStatus = true;

    public enum MedicationType {
        CAPSULE,
        TABLET,
        LIQUID,
        CREAM,
        GEL,
        INHALATION,
        INJECTION,
        SPRAY
    }

    public enum Unit {
        MG,
        MCG,
        G,
        ML,
        PERCENT
    }

    @PrePersist
    @PreUpdate
    private void validateEnumValues() {
        if (type != null && !EnumUtils.isValidEnum(MedicationType.class, type.name())) {
            throw new IllegalArgumentException("Invalid Medication Type");
        }
        if (unit != null && !EnumUtils.isValidEnum(Unit.class, unit.name())) {
            throw new IllegalArgumentException("Invalid Unit");
        }
    }

}
