package com.labmedical.backend.dtos.patients;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.labmedical.backend.dtos.annotations.gender.GenderDeserializer;
import com.labmedical.backend.dtos.annotations.gender.ValidGender;
import com.labmedical.backend.dtos.annotations.marital_status.MaritalStatusDeserializer;
import com.labmedical.backend.dtos.annotations.marital_status.ValidMaritalStatus;
import com.labmedical.backend.entities.Address;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.entities.Person;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record RequestPatientDTO(

        @NotBlank(message = "Patient name is required")
        @Size(max = 64, message = "Patient name cannot exceed 64 characters")
        @Size(min = 8, message = "Patient name must be at least 8 characters")
        String name,

        @JsonDeserialize(using = GenderDeserializer.class)
        @ValidGender
        @NotNull(message = "Patient gender is required")
        Person.Gender gender,

        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Invalid CPF format, (e.g., \"XXX.XXX" +
                ".XXX-XX\")")
        @NotBlank(message = "CPF is required")
        String cpf,

        @Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}", message = "Invalid phone number format (e.g., " +
                "(XX) XXXX-XXXX or (XX) XXXXX-XXXX)")
        @NotBlank(message = "Patient Phone number is required")
        String phone,

        @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*" +
                "(\\.[A-Za-z]{2,})$", message = "Invalid e-mail address format (e.g., username@domain.com)")
        @NotBlank(message = "Patient e-mail address is required")
        String email,

        Boolean status,

        @NotNull(message = "Date of Birth is required")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dateOfBirth,

        @NotBlank(message = "Rg with issuing authority is required")
        String rgWithIssuingAuthority,

        @JsonDeserialize(using = MaritalStatusDeserializer.class)
        @ValidMaritalStatus
        @NotNull(message = "Marital Status is required")
        Patient.MaritalStatus maritalStatus,

        @Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}", message = "Invalid Emergency Contact phone number " +
                "format (e.g., (XX) XXXX-XXXX or (XX) XXXXX-XXXX)")
        @NotBlank(message = "Emergency Contact number is required")
        String emergencyContact,

        String allergies,

        String specificCare,

        String insurance,

        String insuranceNumber,

        LocalDate insuranceValidity,

        @NotNull(message = "Patient Address is required")
        @Valid
        Address address
) {
}
