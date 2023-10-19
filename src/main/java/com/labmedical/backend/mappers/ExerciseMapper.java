package com.labmedical.backend.mappers;

<<<<<<< HEAD

=======
>>>>>>> f704553cb2ca39283a984f7b6f8b99ed375500a0
import com.labmedical.backend.dtos.exercises.RequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.ResponseExerciseDTO;
import com.labmedical.backend.entities.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExerciseMapper {

    Exercise map(RequestExerciseDTO source);

    Exercise map(ResponseExerciseDTO source);

<<<<<<< HEAD
    RequestExerciseDTO mapToRequestExerciseDTO(Exercise source);

    ResponseExerciseDTO mapToResponseExerciseDTO(Exercise source);


}
=======
    RequestExerciseDTO mapToPostRequestExerciseDTO(Exercise source);

    ResponseExerciseDTO mapToPostResponseExerciseDTO(Exercise source);

}
>>>>>>> f704553cb2ca39283a984f7b6f8b99ed375500a0
