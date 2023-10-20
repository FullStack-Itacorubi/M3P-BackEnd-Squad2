package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.medications.RequestMedicationDTO;
import com.labmedical.backend.dtos.medications.ResponseMedicationDTO;
import com.labmedical.backend.entities.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MedicationMapper {

    Medication map(RequestMedicationDTO source);

    Medication map(ResponseMedicationDTO source);

    RequestMedicationDTO mapToRequestMedicationDTO(Medication source);

    ResponseMedicationDTO mapToResponseMedicationDTO(Medication source);

}
