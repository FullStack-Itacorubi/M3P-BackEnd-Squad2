package com.labmedical.backend.services;

import com.labmedical.backend.dtos.logs.PostRequestDietDTO;
import com.labmedical.backend.dtos.logs.PostResponseDietDTO;
import com.labmedical.backend.repositories.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface DietService {

    PostResponseDietDTO createDiet(PostRequestDietDTO postRequestDietDTO);

    PostResponseDietDTO updateDiet(Long id, PostRequestDietDTO postRequestDietDTO);
}
