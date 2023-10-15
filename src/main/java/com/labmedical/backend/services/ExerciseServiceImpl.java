package com.labmedical.backend.services;

import com.labmedical.backend.dtos.exercises.PostRequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.PostResponseExerciseDTO;
import com.labmedical.backend.entities.Exercise;
import com.labmedical.backend.mappers.ExerciseMapper;
import com.labmedical.backend.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseMapper exerciseMapper;

    @Override
    public PostResponseExerciseDTO createExercise(PostRequestExerciseDTO postRequestExerciseDTO) {
        Exercise exerciseToSave = exerciseMapper.map(postRequestExerciseDTO);
        return exerciseMapper
                .mapToPostResponseExerciseDTO(exerciseRepository.save(exerciseToSave));
    }
}
