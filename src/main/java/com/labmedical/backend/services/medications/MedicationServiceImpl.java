package com.labmedical.backend.services.medications;

import com.labmedical.backend.dtos.medications.RequestMedicationDTO;
import com.labmedical.backend.dtos.medications.ResponseMedicationDTO;
import com.labmedical.backend.mappers.MedicationMapper;
import com.labmedical.backend.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationServiceImpl implements MedicationService{

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private MedicationMapper medicationMapper;

    @Override
    public ResponseMedicationDTO saveMedication(RequestMedicationDTO requestMedicationDTO) {
        return medicationMapper.mapToResponseMedicationDTO(
                medicationRepository.save(medicationMapper.map(requestMedicationDTO))
        );
    }
}
