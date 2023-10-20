package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.patients.RequestPatientDTO;
import com.labmedical.backend.dtos.patients.ResponsePatientDTO;
import com.labmedical.backend.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    Patient map(RequestPatientDTO source);

    Patient map(ResponsePatientDTO source);

    RequestPatientDTO mapToRequestPatientDTO(Patient source);

    ResponsePatientDTO mapToResponsePatientDTO(Patient source);
}
