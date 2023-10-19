package com.labmedical.backend.services;

<<<<<<< HEAD
import com.labmedical.backend.dtos.exercises.RequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.ResponseExerciseDTO;
=======
import com.labmedical.backend.dtos.exercises.PostRequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.PostResponseExerciseDTO;
>>>>>>> ab87a59 (feat(create-exercise): add endpoint to POST request and functions on controller and service)
import com.labmedical.backend.entities.Exercise;
import com.labmedical.backend.mappers.ExerciseMapper;
import com.labmedical.backend.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.NoSuchElementException;
import java.util.Optional;

=======
>>>>>>> ab87a59 (feat(create-exercise): add endpoint to POST request and functions on controller and service)
@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseMapper exerciseMapper;

    @Override
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> parent of 6439e70 (fix(save-exercise): add relationship between exercise and patient to save exercise function)
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

    @Override
    public void deleteExerciseById(Long id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if (exerciseOptional.isEmpty()) {
            throw new NoSuchElementException();
        }else{
            exerciseRepository.delete(exerciseOptional.get());
        }
    }
<<<<<<< HEAD
=======
=======
    public PostResponseExerciseDTO createExercise(PostRequestExerciseDTO postRequestExerciseDTO) {
        Exercise exerciseToSave = exerciseMapper.map(postRequestExerciseDTO);
        return exerciseMapper
                .mapToPostResponseExerciseDTO(exerciseRepository.save(exerciseToSave));
    }
>>>>>>> ab87a59 (feat(create-exercise): add endpoint to POST request and functions on controller and service)
>>>>>>> parent of 6439e70 (fix(save-exercise): add relationship between exercise and patient to save exercise function)
}
