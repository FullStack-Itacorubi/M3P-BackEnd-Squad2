package com.labmedical.backend.services;

import com.labmedical.backend.dtos.appointment.AppointmentRequestDTO;
import com.labmedical.backend.dtos.appointment.AppointmentResponseDTO;

import java.util.List;

public interface AppointmentService {
    AppointmentResponseDTO createAppointment(AppointmentRequestDTO requestDTO);
}

