package com.labmedical.backend.services;

import com.labmedical.backend.dtos.logs.PostRequestDietDTO;
import com.labmedical.backend.dtos.logs.PostResponseDietDTO;
import com.labmedical.backend.entities.Diet;
import com.labmedical.backend.mappers.DietMapper;
import com.labmedical.backend.repositories.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

@Service
public class DietServiceImpl implements DietService {

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private DietMapper dietMapper;

    public PostResponseDietDTO createDiet(PostRequestDietDTO postRequestDietDTO){
        Diet dietToSave = dietMapper.map(postRequestDietDTO);
        return dietMapper.mapToPostResponseDietDTO(dietRepository.save(dietToSave));
    }
}
