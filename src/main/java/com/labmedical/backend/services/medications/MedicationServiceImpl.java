package com.labmedical.backend.services.medications;

import com.labmedical.backend.dtos.medications.RequestMedicationDTO;
import com.labmedical.backend.dtos.medications.ResponseMedicationDTO;
import com.labmedical.backend.entities.Medication;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.mappers.MedicationMapper;
import com.labmedical.backend.repositories.MedicationRepository;
import com.labmedical.backend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private MedicationMapper medicationMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public ResponseMedicationDTO saveMedication(RequestMedicationDTO requestMedicationDTO, Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if (patientOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        Medication medicationToSave = medicationMapper.map(requestMedicationDTO);
        medicationToSave.setSystemStatus(true);
        medicationToSave.setPatient(patientOptional.get());

        return medicationMapper
                .mapToResponseMedicationDTO(medicationRepository.save(medicationToSave));
    }

    @Override
    public ResponseMedicationDTO updateMedication(Long id, RequestMedicationDTO requestMedicationDTO) {
        Optional<Medication> medicationOptional = medicationRepository.findById(id);
        if (medicationOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        Long patientId = medicationOptional.get().getPatient().getId();
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if(patientOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }

        Medication medicationToUpdate = medicationMapper.map(requestMedicationDTO);
        medicationToUpdate.setPatient(patientOptional.get());
        medicationToUpdate.setId(id);

        return medicationMapper.mapToResponseMedicationDTO(medicationRepository.save(medicationToUpdate));
    }

    @Override
    public ResponseMedicationDTO findMedicationById(Long id) {
        Optional<Medication> medicationOptional = medicationRepository.findById(id);
        if (medicationOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return medicationMapper.mapToResponseMedicationDTO(medicationOptional.get());
    }

    @Override
    public List<ResponseMedicationDTO> findAllByName(String patientName) {
        if (patientName != null) {
            List<Medication> medicationList = medicationRepository.findAllByPatientName(patientName);
            if (medicationList == null || medicationList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, patientName +
                        " has no exams");
            }
            return medicationList
                    .stream()
                    .map(medicationMapper::mapToResponseMedicationDTO).toList();
        }
        return medicationRepository.findAll()
                .stream()
                .map(medicationMapper::mapToResponseMedicationDTO)
                .toList();
    }

    @Override
    public void deleteMedicationById(Long id) {
        Optional<Medication> medicationOptional = medicationRepository.findById(id);
        if (medicationOptional.isEmpty()) {
            throw new NoSuchElementException();
        }else{
            medicationRepository.delete(medicationOptional.get());
        }

    }
}
