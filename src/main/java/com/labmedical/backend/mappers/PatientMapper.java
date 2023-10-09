package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.patients.PatientPostRequestDTO;
import com.labmedical.backend.dtos.patients.PatientPostResponseDTO;
import com.labmedical.backend.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    Patient map(PatientPostRequestDTO source);

    Patient map(PatientPostResponseDTO source);

    PatientPostRequestDTO mapToPatientRequestDto(Patient source);

    PatientPostResponseDTO mapToPatientPostResponseDTO(Patient source);

}
