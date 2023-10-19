package com.labmedical.backend.mappers;


import com.labmedical.backend.dtos.exercises.RequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.ResponseExerciseDTO;
import com.labmedical.backend.entities.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExerciseMapper {

    Exercise map(RequestExerciseDTO source);

    Exercise map(ResponseExerciseDTO source);

    RequestExerciseDTO mapToRequestExerciseDTO(Exercise source);

    ResponseExerciseDTO mapToResponseExerciseDTO(Exercise source);


}
