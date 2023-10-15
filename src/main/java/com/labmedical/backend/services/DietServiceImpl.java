package com.labmedical.backend.services;

import com.labmedical.backend.dtos.diets.GetResponseDietDTO;
import com.labmedical.backend.dtos.diets.PostRequestDietDTO;
import com.labmedical.backend.dtos.diets.PostResponseDietDTO;
import com.labmedical.backend.entities.Diet;
import com.labmedical.backend.mappers.DietMapper;
import com.labmedical.backend.repositories.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Override
    public PostResponseDietDTO updateDiet(Long id, PostRequestDietDTO postRequestDietDTO) {
        Optional<Diet> dietOptional = dietRepository.findById(id);
        if (dietOptional.isEmpty()) {
            throw new NoSuchElementException();
        }

        Diet dietToUpdate = dietMapper.map(postRequestDietDTO);
        dietToUpdate.setId(id);

        return dietMapper.mapToPostResponseDietDTO(dietRepository.save(dietToUpdate));
    }

    @Override
    public GetResponseDietDTO findExamById(Long id) {
        Optional<Diet> examOptional = dietRepository.findById(id);
        if (examOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return dietMapper.mapToGetResponseDietDTO(examOptional.get());
    }
}
