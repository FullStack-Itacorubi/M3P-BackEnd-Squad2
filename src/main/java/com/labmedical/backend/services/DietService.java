package com.labmedical.backend.services;


import com.labmedical.backend.dtos.diets.GetResponseDietDTO;
import com.labmedical.backend.dtos.diets.RequestDietDTO;
import com.labmedical.backend.dtos.diets.ResponseDietDTO;

import java.util.List;

public interface DietService {


    ResponseDietDTO createDiet(RequestDietDTO requestDietDTO, Long patientId);

    ResponseDietDTO updateDiet(Long id, RequestDietDTO requestDietDTO);

    GetResponseDietDTO findDietById(Long id);

    void deleteDietById(Long id);

    List<GetResponseDietDTO> findAllByName(String patientName);
}
