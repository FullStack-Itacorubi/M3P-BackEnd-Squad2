package com.labmedical.backend.services;

import com.labmedical.backend.dtos.diets.PostRequestDietDTO;
import com.labmedical.backend.dtos.diets.PostResponseDietDTO;
import com.labmedical.backend.entities.Diet;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.mappers.DietMapper;
import com.labmedical.backend.repositories.DietRepository;
import com.labmedical.backend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class DietServiceImpl implements DietService {

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private DietMapper dietMapper;

    @Autowired
    private PatientRepository patientRepository;

    public PostResponseDietDTO createDiet(PostRequestDietDTO postRequestDietDTO
            , Long patientId
    ){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if(patientOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        Diet dietToSave = dietMapper.map(postRequestDietDTO);
        dietToSave.setSystemStatus(true);
        dietToSave.setPatient(patientOptional.get());

        return dietMapper
                .mapToPostResponseDietDTO(dietRepository.save(dietToSave));
    }
}
