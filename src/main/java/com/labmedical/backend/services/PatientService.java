package com.labmedical.backend.services;

import com.labmedical.backend.dtos.patients.GetResponsePatientDTO;
import com.labmedical.backend.dtos.patients.PostRequestPatientDTO;
import com.labmedical.backend.dtos.patients.PostResponsePatientDTO;
import com.labmedical.backend.dtos.patients.PutRequestPatientDTO;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.mappers.AddressMapper;
import com.labmedical.backend.mappers.PatientMapper;
import com.labmedical.backend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private AddressMapper addressMapper;


    public PostResponsePatientDTO savePatient(PostRequestPatientDTO patient) {
        Patient patientToSave = this.patientMapper.map(patient);
        patientToSave.setStatus(true);

        return patientMapper.mapToPostResponsePatientDTO(patientRepository.save(patientToSave));

    }

    public List<GetResponsePatientDTO> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::mapToGetResponsePatientDTO)
                .toList();

    }

    public GetResponsePatientDTO findPatientById(Long id) {
        return  patientMapper.mapToGetResponsePatientDTO(patientRepository.findById(id).get());
    }


    public PostResponsePatientDTO replacePatientData(Long id, PutRequestPatientDTO patient) {

        Patient patientToUpdate = patientMapper.map(findPatientById(id));
        Patient newDataPatient = patientMapper.map(patient);
        newDataPatient.setId(id);


        return patientMapper.mapToPostResponsePatientDTO(patientRepository.save(newDataPatient));
    }
}
