package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.diets.GetResponseDietDTO;
import com.labmedical.backend.dtos.diets.RequestDietDTO;
import com.labmedical.backend.dtos.diets.ResponseDietDTO;
import com.labmedical.backend.entities.Diet;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DietMapper {
    Diet map(RequestDietDTO source);

    Diet map(ResponseDietDTO source);

    Diet map(GetResponseDietDTO source);

    RequestDietDTO mapToPostRequestDietDTO(Diet source);

    ResponseDietDTO mapToPostResponseDietDTO(Diet source);

    GetResponseDietDTO mapToGetResponseDietDTO(Diet source);

}
