package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.patients.PostRequestPatientDTO;
import com.labmedical.backend.dtos.patients.PostResponsePatientDTO;
import com.labmedical.backend.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    Patient map(PostRequestPatientDTO source);

    Patient map(PostResponsePatientDTO source);

    PostRequestPatientDTO mapToPostRequestPatientDTO(Patient source);

    PostResponsePatientDTO mapToPostResponsePatientDTO(Patient source);

}
