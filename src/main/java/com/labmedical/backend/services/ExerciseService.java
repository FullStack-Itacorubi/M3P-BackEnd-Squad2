package com.labmedical.backend.services;

import com.labmedical.backend.dtos.exercises.RequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.ResponseExerciseDTO;

import java.util.List;

public interface ExerciseService {

    ResponseExerciseDTO createExercise(RequestExerciseDTO requestExerciseDTO, Long patientId);

    ResponseExerciseDTO updateExercise(Long id, RequestExerciseDTO requestExerciseDTO);

    ResponseExerciseDTO findExerciseById(Long id);

    void deleteExerciseById(Long id);

    List<ResponseExerciseDTO> findAllByName(String patientName);

}

