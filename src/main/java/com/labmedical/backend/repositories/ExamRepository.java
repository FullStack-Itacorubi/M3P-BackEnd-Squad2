package com.labmedical.backend.repositories;

import com.labmedical.backend.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository <Exam, Long> {
}
