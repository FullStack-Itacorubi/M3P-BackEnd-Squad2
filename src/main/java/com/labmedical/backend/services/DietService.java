package com.labmedical.backend.services;

import com.labmedical.backend.dtos.diet.PostRequestDietDTO;
import com.labmedical.backend.dtos.diet.PostResponseDietDTO;

public interface DietService {


    PostResponseDietDTO createDiet(PostRequestDietDTO postRequestDietDTO
            , Long patientId
    );
}
