package com.labmedical.backend.repositories;

import com.labmedical.backend.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository <Exam, Long> {

    @Query( "SELECT e FROM Exam e " +
            "INNER JOIN Patient p " +
            "ON e.patient.Id = p.id " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :patientName ,'%'))")
    List<Exam> findAllByPatientName(String patientName);
}
