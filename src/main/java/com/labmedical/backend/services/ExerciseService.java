package com.labmedical.backend.services;

import com.labmedical.backend.dtos.exercises.RequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.ResponseExerciseDTO;

public interface ExerciseService {

    ResponseExerciseDTO createExercise(RequestExerciseDTO requestExerciseDTO);

    ResponseExerciseDTO updateExercise(Long id, RequestExerciseDTO requestExerciseDTO);
}
