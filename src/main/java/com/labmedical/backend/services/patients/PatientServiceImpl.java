package com.labmedical.backend.services.patients;

import com.labmedical.backend.dtos.patients.RecordsDTO;
import com.labmedical.backend.dtos.patients.RequestPatientDTO;
import com.labmedical.backend.dtos.patients.ResponsePatientDTO;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.mappers.AddressMapper;
import com.labmedical.backend.mappers.PatientMapper;
import com.labmedical.backend.repositories.PatientRepository;
import com.labmedical.backend.services.addresses.AddressServiceImpl;
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
    private AddressServiceImpl addressServiceImpl;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private AddressMapper addressMapper;


    public ResponsePatientDTO savePatient(RequestPatientDTO patient) {
        Patient patientToSave = this.patientMapper.map(patient);
        patientToSave.setStatus(true);

        return patientMapper.mapToPostResponsePatientDTO(patientRepository.save(patientToSave));

    }
    public ResponsePatientDTO updatePatientData(Long id, RequestPatientDTO patient) {

        patientMapper.map(findPatientById(id));
        Patient newDataPatient = patientMapper.map(patient);
        newDataPatient.setId(id);

        Long addressId = addressServiceImpl.findAddresById(newDataPatient.getId()).id();
        newDataPatient.getAddress().setId(addressId);

        return patientMapper.mapToPostResponsePatientDTO(patientRepository.save(newDataPatient));
    }

    public void deletePatient(Long id) {
//TODO : exception to not dlete patient with exercise, diet, exam or appointment"
        Patient patientToDelete = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(patientToDelete.getMedicationList() != null || patientToDelete.getAppointment() != null) {
            throw new IllegalArgumentException();
        }else {patientRepository.delete(patientToDelete);}
    }
    public ResponsePatientDTO findPatientById(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return patientMapper.mapToResponsePatientDTO(patientOptional.get());
        }
    }
    public RecordsDTO searchRecordsById(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            throw new NoSuchElementException("Patient not found");
        }
        Patient patient = patientOptional.get();
        return PatientMapper.INSTANCE.mapToRecordsDTO(patient);
    }

    ;

    public RecordsDTO searchRecordsByName(String name) {
        Optional<Patient> patientOptional = patientRepository.findByName(name);
        if (patientOptional.isEmpty()) {
            // Handle the case where the patient is not found, you can return null or some other response
            // For example, you can return an empty RecordsDTO as a placeholder
            throw new NoSuchElementException();
        }
        Patient patient = patientOptional.get();
        return PatientMapper.INSTANCE.mapToRecordsDTO(patient);
    }

    ;

    public List<ResponsePatientDTO> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::mapToGetResponsePatientDTO)
                .toList();

    }

    public List<RecordsDTO> listAllProntuarios() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::mapToRecordsDTO)
                .toList();
    }
}

