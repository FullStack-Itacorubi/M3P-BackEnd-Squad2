package com.labmedical.backend.services;

import com.labmedical.backend.dtos.appointment.AppointmentRequestDTO;
import com.labmedical.backend.dtos.appointment.AppointmentResponseDTO;
import com.labmedical.backend.dtos.appointment.UpdateAppointmentRequestDTO;
import com.labmedical.backend.entities.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    AppointmentResponseDTO createAppointment(AppointmentRequestDTO requestDTO);

    boolean deleteAppointment(Long id);

    Optional<AppointmentResponseDTO> getAppointmentById(Long id);

    List<AppointmentResponseDTO> getAppointmentsByPatient(Long patientId);

    List<AppointmentResponseDTO> getAllAppointments();
    void updateAppointment(Appointment existingAppointment, UpdateAppointmentRequestDTO updateAppointmentRequest);
}

