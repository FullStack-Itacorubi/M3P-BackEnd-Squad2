package com.labmedical.backend.services.patients;

import com.labmedical.backend.dtos.patients.RequestPatientDTO;
import com.labmedical.backend.dtos.patients.ResponsePatientDTO;

import java.util.List;

public interface PatientService {

    ResponsePatientDTO savePatient(RequestPatientDTO patient);

    List<ResponsePatientDTO> findAll();

    ResponsePatientDTO findPatientById(Long id);


    ResponsePatientDTO replacePatientData(Long id, RequestPatientDTO patient);

    void deletePatient(Long id);
}

