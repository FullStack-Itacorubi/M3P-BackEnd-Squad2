package com.labmedical.backend.repositories;

import com.labmedical.backend.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    @Query( "SELECT m FROM Medication m " +
            "INNER JOIN Patient p " +
            "ON m.patient.Id = p.id " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :patientName ,'%'))")
    List<Medication> findAllByPatientName(String patientName);
}
