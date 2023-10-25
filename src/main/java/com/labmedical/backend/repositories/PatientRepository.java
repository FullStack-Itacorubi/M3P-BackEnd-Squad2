package com.labmedical.backend.repositories;

import com.labmedical.backend.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT DISTINCT p FROM Patient p " +
            "LEFT JOIN FETCH p.examList exams " +
            "LEFT JOIN FETCH p.dietList diets " +
            "LEFT JOIN FETCH p.exerciseList exercises " +
            "LEFT JOIN FETCH p.appointment appointments " +
            "WHERE p.id = :id")
    Optional<Patient> findByIdWithRelatedData(@Param("id") Long id);

    @Query("SELECT DISTINCT p FROM Patient p " +
            "LEFT JOIN FETCH p.examList exams " +
            "LEFT JOIN FETCH p.dietList diets " +
            "LEFT JOIN FETCH p.exerciseList exercises " +
            "LEFT JOIN FETCH p.appointment appointments " +
            "WHERE p.name = :name")
    Optional<Patient> findByName(@Param("name") String name);
}
