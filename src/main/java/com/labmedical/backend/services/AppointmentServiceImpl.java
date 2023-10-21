package com.labmedical.backend.services;

import com.labmedical.backend.dtos.appointment.AppointmentRequestDTO;
import com.labmedical.backend.dtos.appointment.AppointmentResponseDTO;
import com.labmedical.backend.entities.Appointment;
import com.labmedical.backend.mappers.AppointmentMapper;
import com.labmedical.backend.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    @Override
    public boolean deleteAppointment(Long id) {
        if (id == null) {
            return false;
        }

        try {
            Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
            if (appointmentOptional.isPresent()) {
                appointmentRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Optional<AppointmentResponseDTO> getAppointmentById(Long id) {
        try {
            Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
            if (appointmentOptional.isPresent()) {
                AppointmentResponseDTO responseDTO = AppointmentMapper.INSTANCE.toDTO(appointmentOptional.get());
                return Optional.of(responseDTO);
            } else {
                return Optional.empty();
            }
        } catch (Exception ex) {
            return Optional.empty(); // Error occurred
        }
    }

}
