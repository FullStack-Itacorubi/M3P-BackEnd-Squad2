package com.labmedical.backend.services.patients;

import com.labmedical.backend.dtos.patients.RequestPatientDTO;
import com.labmedical.backend.dtos.patients.ResponsePatientDTO;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.mappers.AddressMapper;
import com.labmedical.backend.mappers.PatientMapper;
import com.labmedical.backend.repositories.PatientRepository;
import com.labmedical.backend.services.AddressService;
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


    public ResponsePatientDTO savePatient(RequestPatientDTO patient) {
        Patient patientToSave = this.patientMapper.map(patient);
        patientToSave.setStatus(true);

        return patientMapper.mapToResponsePatientDTO(patientRepository.save(patientToSave));

    }

    public List<ResponsePatientDTO> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::mapToResponsePatientDTO)
                .toList();

    }

    public ResponsePatientDTO findPatientById(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);

        if (patientOptional.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return patientMapper.mapToResponsePatientDTO(patientOptional.get());
        }
    }


    public ResponsePatientDTO replacePatientData(Long id, RequestPatientDTO patient) {

        patientMapper.map(findPatientById(id));
        Patient newDataPatient = patientMapper.map(patient);
        newDataPatient.setId(id);

        return patientMapper.mapToResponsePatientDTO(patientRepository.save(newDataPatient));
    }

    public void deletePatient(Long id) {
        Patient patientToDelete = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        patientRepository.delete(patientToDelete);
    }
}

