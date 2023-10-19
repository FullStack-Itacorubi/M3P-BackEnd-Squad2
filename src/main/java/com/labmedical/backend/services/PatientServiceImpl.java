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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressService addressService;

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
        Optional<Patient> patientOptional = patientRepository.findById(id);

        if (patientOptional.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return patientMapper.mapToGetResponsePatientDTO(patientOptional.get());
        }
    }


    public PostResponsePatientDTO replacePatientData(Long id, PutRequestPatientDTO patient) {

        patientMapper.map(findPatientById(id));
        Patient newDataPatient = patientMapper.map(patient);
        newDataPatient.setId(id);


        return patientMapper.mapToPostResponsePatientDTO(patientRepository.save(newDataPatient));
    }

    public void deletePatient(Long id) {
//TODO : exception to not dlete patient with exercise, diet, exam or appointment"
        Patient patientToDelete = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        patientRepository.delete(patientToDelete);
    }
}

