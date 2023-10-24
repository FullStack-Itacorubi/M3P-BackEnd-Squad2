package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.patients.RequestPatientDTO;
import com.labmedical.backend.dtos.patients.ResponsePatientDTO;
import com.labmedical.backend.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    Patient map(RequestPatientDTO source);

    Patient map(ResponsePatientDTO source);

    RequestPatientDTO mapToRequestPatientDTO(Patient source);

    ResponsePatientDTO mapToResponsePatientDTO(Patient source);
    @Mappings({
            @Mapping(target = "exerciseList", source = "exerciseList"),
            @Mapping(target = "dietList", source = "dietList"),
            @Mapping(target = "examList", source = "examList"),
            @Mapping(target = "appointment", source = "appointment")
    })
    ResponsePatientDTO mapToGetResponsePatientDTO(Patient source);

    ResponsePatientDTO mapToPostResponsePatientDTO(Patient source);

}
