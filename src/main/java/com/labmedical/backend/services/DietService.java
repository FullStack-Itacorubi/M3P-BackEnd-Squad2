package com.labmedical.backend.services;

<<<<<<< HEAD

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
=======
import com.labmedical.backend.dtos.logs.PostRequestDietDTO;
import com.labmedical.backend.dtos.logs.PostResponseDietDTO;
import com.labmedical.backend.repositories.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface DietService {

    PostResponseDietDTO createDiet(PostRequestDietDTO postRequestDietDTO);
>>>>>>> parent of bafef2e (fix(save-diet): add patient relationship when saving a diet instance and fix exception handlers)
}
