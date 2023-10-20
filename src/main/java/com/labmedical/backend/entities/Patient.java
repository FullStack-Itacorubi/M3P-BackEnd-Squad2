package com.labmedical.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient extends Person {

    @NotNull(message = "Date of Birth is required")
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @NotBlank(message = "RG number is required")
    @Size(max = 20, message = "Maximum 20 characters allowed for RG with issuing authority")
    @Column(name = "rg_with_issuing_authority")
    private String rgWithIssuingAuthority;

    @NotNull(message = "Marital Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    @NotBlank(message = "Emergency contact phone number ir required")
    @Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{1,5}-\\d{4,5}", message = "Invalid phone number format (e.g., (99) 9 9999-99999)")
    private String emergencyContact;

    private String allergies;

    @Column(name = "specific_care")
    private String specificCare;

    private String insurance;

    @Column(name = "insurance_number")
    private String insuranceNumber;

    @Column(name = "insurance_validity")
    private LocalDate insuranceValidity;

    @NotNull(message = "Address is required")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Exam> examList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Diet> dietList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Exercise> exerciseList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Medication> medicationList;

    public enum MaritalStatus {
        SINGLE,
        MARRIED,
        DIVORCED,
        WIDOWED
    }
}
