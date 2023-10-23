package com.labmedical.backend.repositories;

import com.labmedical.backend.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a JOIN a.patient p WHERE p.id = :patientId")
    List<Appointment> findByPatientId(Long patientId);
}
