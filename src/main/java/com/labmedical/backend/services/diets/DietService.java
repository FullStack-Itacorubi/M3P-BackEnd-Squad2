package com.labmedical.backend.services.diets;


import com.labmedical.backend.dtos.diets.RequestDietDTO;
import com.labmedical.backend.dtos.diets.ResponseDietDTO;

import java.util.List;

public interface DietService {


    ResponseDietDTO createDiet(RequestDietDTO requestDietDTO, Long patientId);

    ResponseDietDTO updateDiet(Long id, RequestDietDTO requestDietDTO);

    ResponseDietDTO findDietById(Long id);

    void deleteDietById(Long id);

    List<ResponseDietDTO> findAllByName(String patientName);
}
