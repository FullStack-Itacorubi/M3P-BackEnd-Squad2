package com.labmedical.backend.services;

import com.labmedical.backend.dtos.appointment.AppointmentRequestDTO;
import com.labmedical.backend.dtos.appointment.AppointmentResponseDTO;
import com.labmedical.backend.entities.Appointment;
import com.labmedical.backend.mappers.AppointmentMapper;
import com.labmedical.backend.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO requestDTO) {
        Appointment appointment = AppointmentMapper.INSTANCE.toEntity(requestDTO);
        appointmentRepository.save(appointment);
        return AppointmentMapper.INSTANCE.toDTO(appointment);
    }
}
