package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.patients.RecordsDTO;
import com.labmedical.backend.dtos.patients.RequestPatientDTO;
import com.labmedical.backend.dtos.patients.ResponsePatientDTO;
import com.labmedical.backend.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

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

    RecordsDTO mapToRecordsDTO(Patient patient);
}
