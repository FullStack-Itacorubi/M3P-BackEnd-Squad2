package com.labmedical.backend.dtos.patients;

import com.labmedical.backend.entities.Address;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.entities.Person;

import java.time.LocalDate;

public record PatientPostRequestDTO(


        String name,

        Person.Gender gender,

        String cpf,

        String phone,

        String email,

//        Boolean status,

        LocalDate dateOfBirth,

        String rgWithIssuingAuthority,

        Patient.MaritalStatus maritalStatus,

        String emergencyContact,

        String allergies,

        String specificCare,

        String insuranceNumber,

        LocalDate insuranceValidity

//        Address address,

//        Boolean systemStatus
) {
//    public PatientPostRequestDTO(Patient patient){
//        this(
//                patient.getId(),
//                patient.getName(),
//                patient.getGender(),
//                patient.getCpf(),
//                patient.getPhone(),
//                patient.getEmail(),
//                patient.getAllergies(),
//                patient.getRgWithIssuingAuthority(),
//                patient.getSystemStatus(),
//                patient.getDateOfBirth(),
//                patient.getInsurance(),
//                patient.getMaritalStatus(),
//                patient.getEmergencyContact(),
//                patient.getInsuranceNumber(),
//                patient.getSpecificCare(),
//                patient.getAddress(),
//
//                Boolean status,
//
//                LocalDate dateOfBirth,
//
//                String rgWithIssuingAuthority,
//
//                Patient.MaritalStatus maritalStatus,
//
//                String emergencyContact,
//
//                String allergies,
//
//                String specificCare,
//
//                String insuranceNumber,
//
//                LocalDate insuranceValidity,
//
//                Address address,
//
//                Boolean systemStatus
//        )
//    }
}
