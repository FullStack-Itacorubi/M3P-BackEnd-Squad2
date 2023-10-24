package com.labmedical.backend.dtos.patients;

import com.labmedical.backend.entities.*;

import java.time.LocalDate;
import java.util.List;

public record ResponsePatientDTO(

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
        Address address,
        List<Exercise> exerciseList,
        List<Diet> dietList,
        List<Exam> examList,
        List<Appointment> appointment
) {
}
