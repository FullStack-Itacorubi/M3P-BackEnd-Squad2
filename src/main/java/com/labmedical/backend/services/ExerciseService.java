package com.labmedical.backend.services;

import com.labmedical.backend.dtos.exercises.PostRequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.PostResponseExerciseDTO;

public interface ExerciseService {

    PostResponseExerciseDTO createExercise(PostRequestExerciseDTO postRequestExerciseDTO);
}
