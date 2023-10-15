package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.exercises.PostRequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.PostResponseExerciseDTO;
import com.labmedical.backend.entities.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExerciseMapper {

    Exercise map(PostRequestExerciseDTO source);

    Exercise map(PostResponseExerciseDTO source);

    PostRequestExerciseDTO mapToPostRequestExerciseDTO(Exercise source);

    PostResponseExerciseDTO mapToPostResponseExerciseDTO(Exercise source);

}
