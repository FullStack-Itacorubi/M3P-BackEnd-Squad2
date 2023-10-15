package com.labmedical.backend.services;

import com.labmedical.backend.dtos.exercises.RequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.ResponseExerciseDTO;
import com.labmedical.backend.entities.Exercise;
import com.labmedical.backend.mappers.ExerciseMapper;
import com.labmedical.backend.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseMapper exerciseMapper;

    @Override
    public ResponseExerciseDTO createExercise(RequestExerciseDTO requestExerciseDTO) {
        Exercise exerciseToSave = exerciseMapper.map(requestExerciseDTO);
        return exerciseMapper
                .mapToPostResponseExerciseDTO(exerciseRepository.save(exerciseToSave));
    }

    @Override
    public ResponseExerciseDTO updateExercise(Long id, RequestExerciseDTO requestExerciseDTO) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if (exerciseOptional.isEmpty()) {
            throw new NoSuchElementException();
        }

        Exercise exerciseToUpdate = exerciseMapper.map(requestExerciseDTO);
        exerciseToUpdate.setId(id);

        return exerciseMapper.mapToPostResponseExerciseDTO(exerciseRepository.save(exerciseToUpdate));
    }

    @Override
    public ResponseExerciseDTO findExerciseById(Long id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if (exerciseOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return exerciseMapper.mapToPostResponseExerciseDTO(exerciseOptional.get());
    }
}
