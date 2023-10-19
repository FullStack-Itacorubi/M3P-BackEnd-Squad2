package com.labmedical.backend.services;


import com.labmedical.backend.dtos.diets.GetResponseDietDTO;
import com.labmedical.backend.dtos.diets.PostRequestDietDTO;
import com.labmedical.backend.dtos.diets.PostResponseDietDTO;

import java.util.List;

public interface DietService {


    PostResponseDietDTO createDiet(PostRequestDietDTO postRequestDietDTO, Long patientId);

    PostResponseDietDTO updateDiet(Long id, PostRequestDietDTO postRequestDietDTO);

    GetResponseDietDTO findDietById(Long id);

    void deleteDietById(Long id);

    List<GetResponseDietDTO> findAllByName(String patientName);
}
