package com.labmedical.backend.services.medications;

import com.labmedical.backend.dtos.medications.RequestMedicationDTO;
import com.labmedical.backend.dtos.medications.ResponseMedicationDTO;

public interface MedicationService {

    ResponseMedicationDTO saveMedication(RequestMedicationDTO requestMedicationDTO, Long patientId);

    ResponseMedicationDTO updateMedication(Long id, RequestMedicationDTO requestMedicationDTO);

    ResponseMedicationDTO findMedicationById(Long id);
}
