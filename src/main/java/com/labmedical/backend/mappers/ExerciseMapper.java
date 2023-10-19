package com.labmedical.backend.mappers;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> parent of 6439e70 (fix(save-exercise): add relationship between exercise and patient to save exercise function)
import com.labmedical.backend.dtos.exercises.RequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.ResponseExerciseDTO;
=======
import com.labmedical.backend.dtos.exercises.PostRequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.PostResponseExerciseDTO;
>>>>>>> ab87a59 (feat(create-exercise): add endpoint to POST request and functions on controller and service)
import com.labmedical.backend.entities.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExerciseMapper {

<<<<<<< HEAD
    Exercise map(RequestExerciseDTO source);

    Exercise map(ResponseExerciseDTO source);

    RequestExerciseDTO mapToPostRequestExerciseDTO(Exercise source);
<<<<<<< HEAD

    ResponseExerciseDTO mapToPostResponseExerciseDTO(Exercise source);
=======

    ResponseExerciseDTO mapToPostResponseExerciseDTO(Exercise source);
=======
    Exercise map(PostRequestExerciseDTO source);

    Exercise map(PostResponseExerciseDTO source);

    PostRequestExerciseDTO mapToPostRequestExerciseDTO(Exercise source);

    PostResponseExerciseDTO mapToPostResponseExerciseDTO(Exercise source);
>>>>>>> ab87a59 (feat(create-exercise): add endpoint to POST request and functions on controller and service)
>>>>>>> parent of 6439e70 (fix(save-exercise): add relationship between exercise and patient to save exercise function)

}
