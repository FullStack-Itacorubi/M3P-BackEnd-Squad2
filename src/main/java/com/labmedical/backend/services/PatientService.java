package com.labmedical.backend.services;

import com.labmedical.backend.dtos.patients.GetResponsePatientDTO;
import com.labmedical.backend.dtos.patients.PostRequestPatientDTO;
import com.labmedical.backend.dtos.patients.PostResponsePatientDTO;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.mappers.AddressMapper;
import com.labmedical.backend.mappers.PatientMapper;
import com.labmedical.backend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}
