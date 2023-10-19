package com.labmedical.backend.dtos.patients;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.labmedical.backend.dtos.annotations.MaritalStatusDeserializer;
import com.labmedical.backend.dtos.annotations.ValidMaritalStatus;
import com.labmedical.backend.entities.Address;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.entities.Person;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record PostRequestPatientDTO(

        @NotBlank(message = "is required")
        String name,

        @NotNull(message = "is required")
        Person.Gender gender,

        @NotBlank(message = "is required")
        String cpf,

        @Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}", message = "Invalid phone number format (e.g., (XX) XXXX-XXXX or (XX) XXXXX-XXXX)")
        @NotBlank(message = "number is required")
        String phone,

        @Email(message = "Invalid email address")
        @NotBlank(message = "is required")
        String email,

        Boolean status,

        @NotNull(message = "is required")
        LocalDate dateOfBirth,

        @NotBlank(message = "is required")
        String rgWithIssuingAuthority,

        @JsonDeserialize(using = MaritalStatusDeserializer.class)
        @ValidMaritalStatus
        @NotNull(message = "Marital Status is required")
        Patient.MaritalStatus maritalStatus,

        @NotBlank(message = "number is required")
        String emergencyContact,

        String allergies,

        @Column(name = "specific_care")
        String specificCare,

        String insurance,

        @Column(name = "insurance_number")
        String insuranceNumber,

        @Column(name = "insurance_validity")
        LocalDate insuranceValidity,

        @NotNull(message = "is required")
        Address address
) {
}
