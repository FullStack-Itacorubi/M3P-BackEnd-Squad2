package com.labmedical.backend.services;

import com.labmedical.backend.dtos.exercises.RequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.ResponseExerciseDTO;

public interface ExerciseService {

<<<<<<< HEAD
    ResponseExerciseDTO createExercise(RequestExerciseDTO requestExerciseDTO, Long patientId);

    ResponseExerciseDTO updateExercise(Long id, RequestExerciseDTO requestExerciseDTO);

    ResponseExerciseDTO findExerciseById(Long id);

    void deleteExerciseById(Long id);
}
=======
    ResponseExerciseDTO createExercise(RequestExerciseDTO requestExerciseDTO);

    ResponseExerciseDTO updateExercise(Long id, RequestExerciseDTO requestExerciseDTO);
}
>>>>>>> f704553cb2ca39283a984f7b6f8b99ed375500a0
