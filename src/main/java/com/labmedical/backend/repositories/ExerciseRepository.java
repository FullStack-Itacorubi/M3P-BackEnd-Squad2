package com.labmedical.backend.repositories;

import com.labmedical.backend.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query( "SELECT e FROM Exercise e " +
            "INNER JOIN Patient p " +
            "ON e.patient.Id = p.id " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :patientName ,'%'))")
    List<Exercise> findAllByPatientName(String patientName);
}
