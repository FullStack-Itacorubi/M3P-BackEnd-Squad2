package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.diets.GetResponseDietDTO;
import com.labmedical.backend.dtos.diets.PostRequestDietDTO;
import com.labmedical.backend.dtos.diets.PostResponseDietDTO;
import com.labmedical.backend.entities.Diet;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DietMapper {
    Diet map(PostRequestDietDTO source);

    Diet map(PostResponseDietDTO source);

    Diet map(GetResponseDietDTO source);

    PostRequestDietDTO mapToPostRequestDietDTO(Diet source);

    PostResponseDietDTO mapToPostResponseDietDTO(Diet source);

    GetResponseDietDTO mapToGetResponseDietDTO(Diet source);

}
