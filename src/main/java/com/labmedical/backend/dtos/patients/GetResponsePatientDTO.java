package com.labmedical.backend.dtos.patients;

import com.labmedical.backend.entities.Address;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.entities.Person;

import java.time.LocalDate;

public record GetResponsePatientDTO(
        Long id,

        String name,

        Person.Gender gender,

        String cpf,

        String phone,

        String email,

        Boolean status,

        LocalDate dateOfBirth,

        String rgWithIssuingAuthority,

        Patient.MaritalStatus maritalStatus,

        String emergencyContact,

        String allergies,

        String specificCare,


        String insuranceNumber,

        LocalDate insuranceValidity,

        Address address
) {
}
