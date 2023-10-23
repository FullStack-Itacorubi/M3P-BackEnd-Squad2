package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.patients.GetResponsePatientDTO;
import com.labmedical.backend.dtos.patients.PostRequestPatientDTO;
import com.labmedical.backend.dtos.patients.PostResponsePatientDTO;
import com.labmedical.backend.dtos.patients.PutRequestPatientDTO;
import com.labmedical.backend.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    Patient map(PostRequestPatientDTO source);

    Patient map(PostResponsePatientDTO source);

    Patient map(GetResponsePatientDTO source);

    Patient map(PutRequestPatientDTO source);

    @Mappings({
            @Mapping(target = "exerciseList", source = "exerciseList"),
            @Mapping(target = "dietList", source = "dietList"),
            @Mapping(target = "examList", source = "examList"),
            @Mapping(target = "appointment", source = "appointment")
    })
    GetResponsePatientDTO mapToGetResponsePatientDTO(Patient source);

    PostResponsePatientDTO mapToPostResponsePatientDTO(Patient source);

}
