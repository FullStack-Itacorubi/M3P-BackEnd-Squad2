package com.labmedical.backend.mappers;
<<<<<<< HEAD

import com.labmedical.backend.dtos.diets.GetResponseDietDTO;
import com.labmedical.backend.dtos.diets.PostRequestDietDTO;
import com.labmedical.backend.dtos.diets.PostResponseDietDTO;
=======
import com.labmedical.backend.dtos.logs.PostRequestDietDTO;
import com.labmedical.backend.dtos.logs.PostResponseDietDTO;
>>>>>>> parent of bafef2e (fix(save-diet): add patient relationship when saving a diet instance and fix exception handlers)
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
<<<<<<< HEAD

    GetResponseDietDTO mapToGetResponseDietDTO(Diet source);

=======
>>>>>>> parent of bafef2e (fix(save-diet): add patient relationship when saving a diet instance and fix exception handlers)
}
