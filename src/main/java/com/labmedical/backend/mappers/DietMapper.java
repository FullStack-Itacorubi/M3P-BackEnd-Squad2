package com.labmedical.backend.mappers;
import com.labmedical.backend.dtos.diet.PostRequestDietDTO;
import com.labmedical.backend.dtos.diet.PostResponseDietDTO;
import com.labmedical.backend.entities.Diet;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DietMapper {
    Diet map(PostRequestDietDTO source);

    Diet map(PostResponseDietDTO source);

    PostRequestDietDTO mapToPostRequestDietDTO(Diet source);

    PostResponseDietDTO mapToPostResponseDietDTO(Diet source);


}
