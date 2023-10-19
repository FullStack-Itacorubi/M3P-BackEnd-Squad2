package com.labmedical.backend.services;

<<<<<<< HEAD
import com.labmedical.backend.dtos.exercises.RequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.ResponseExerciseDTO;

public interface ExerciseService {

    ResponseExerciseDTO createExercise(RequestExerciseDTO requestExerciseDTO, Long patientId);

    ResponseExerciseDTO updateExercise(Long id, RequestExerciseDTO requestExerciseDTO);

    ResponseExerciseDTO findExerciseById(Long id);

    void deleteExerciseById(Long id);
=======
import com.labmedical.backend.dtos.exercises.PostRequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.PostResponseExerciseDTO;

public interface ExerciseService {

    PostResponseExerciseDTO createExercise(PostRequestExerciseDTO postRequestExerciseDTO);
>>>>>>> ab87a59 (feat(create-exercise): add endpoint to POST request and functions on controller and service)
}
