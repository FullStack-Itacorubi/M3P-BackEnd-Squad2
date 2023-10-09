package com.labmedical.backend.services;

import com.labmedical.backend.dtos.patients.PatientPostRequestDTO;
import com.labmedical.backend.dtos.patients.PatientPostResponseDTO;
import com.labmedical.backend.entities.Address;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.mappers.AddressMapper;
import com.labmedical.backend.mappers.PatientMapper;
import com.labmedical.backend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired AddressService addressService;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private AddressMapper addressMapper;


    public PatientPostResponseDTO savePatient(PatientPostRequestDTO patient) {
//        Address patienteAddress = this.addressService.saveAddress(patient.address());
        Patient patientToSave = this.patientMapper.map(patient);
//        patientToSave.setStatus();
//        Long addressId = patienteAddress.getId();
//        PatientWithAddressId patientToSaveAddressId = this.patientMapper.mapToPatienteWithAddressId(patientToSave);
//        patientToSave.setAddress(this.addressMapper.map(this.addressService.findAddresById(patienteAddress.getId())));
        return patientMapper
                .mapToPatientPostResponseDTO(patientRepository.save(patientToSave));

    }
}
