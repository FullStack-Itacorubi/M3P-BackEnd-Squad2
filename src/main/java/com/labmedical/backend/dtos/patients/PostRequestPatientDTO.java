package com.labmedical.backend.dtos.patients;

import com.labmedical.backend.entities.Address;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.entities.Person;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.NonNull;

import java.time.LocalDate;

public record PostRequestPatientDTO(

        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Gender is required")
        Person.Gender gender,

        @NotBlank(message = "CPF is required")
        String cpf,

        @Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}", message = "Invalid phone number format (e.g., (XX) XXXX-XXXX or (XX) XXXXX-XXXX)")
        @NotBlank(message = "Phone number is required")
        String phone,

        @Email(message = "Invalid email address")
        @NotBlank(message = "Email is required")
        String email,

        Boolean status,

        @NotNull(message = "Date of Birth is required")
        LocalDate dateOfBirth,

        @NotBlank(message = "RG is required")
        String rgWithIssuingAuthority,

        @NotNull(message = "Marital Status is required")
        Patient.MaritalStatus maritalStatus,

        @NotBlank(message = "Emergency contact number is required")
        String emergencyContact,

        String allergies,

        @Column(name = "specific_care")
        String specificCare,

        String insurance,

        @Column(name = "insurance_number")
        String insuranceNumber,

        @Column(name = "insurance_validity")
        LocalDate insuranceValidity,

        @NotNull(message = "Address is required")
        Address address
) {
}
