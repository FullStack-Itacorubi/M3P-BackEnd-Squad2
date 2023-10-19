package com.labmedical.backend.repositories;

import com.labmedical.backend.entities.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {

    @Query( "SELECT d FROM Diet d " +
            "INNER JOIN Patient p " +
            "ON d.patient.Id = p.id " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :patientName ,'%'))")
    List<Diet> findAllByPatientName(String patientName);
}
