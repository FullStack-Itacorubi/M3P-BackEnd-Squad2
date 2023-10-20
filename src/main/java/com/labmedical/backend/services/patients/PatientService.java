package com.labmedical.backend.services.patients;

import com.labmedical.backend.dtos.patients.GetResponsePatientDTO;
import com.labmedical.backend.dtos.patients.PostRequestPatientDTO;
import com.labmedical.backend.dtos.patients.PostResponsePatientDTO;
import com.labmedical.backend.dtos.patients.PutRequestPatientDTO;

import java.util.List;

public interface PatientService {

    PostResponsePatientDTO savePatient(PostRequestPatientDTO patient);

    List<GetResponsePatientDTO> findAll();

    GetResponsePatientDTO findPatientById(Long id);


    PostResponsePatientDTO replacePatientData(Long id, PutRequestPatientDTO patient);

    void deletePatient(Long id);
}

