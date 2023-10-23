package com.labmedical.backend.services;

import com.labmedical.backend.dtos.appointment.AppointmentRequestDTO;
import com.labmedical.backend.dtos.appointment.AppointmentResponseDTO;
import com.labmedical.backend.dtos.appointment.UpdateAppointmentRequestDTO;
import com.labmedical.backend.entities.Appointment;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.mappers.AppointmentMapper;
import com.labmedical.backend.repositories.AppointmentRepository;
import com.labmedical.backend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO requestDTO) {
        Long patientId = requestDTO.patientId();
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if (patientOptional.isPresent()) {
            Appointment appointment = AppointmentMapper.INSTANCE.toEntity(requestDTO);
            appointment.setPatient(patientOptional.get());
            appointmentRepository.save(appointment);

            return AppointmentMapper.INSTANCE.toDTO(appointment);
        } else {
            throw new IllegalArgumentException("Patient not found with ID: " + patientId);
        }
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
            return Optional.empty();
        }
    }
    @Override
    public List<AppointmentResponseDTO> getAppointmentsByPatient(Long patientId) {
        List<Appointment> appointments;
        if (patientId != null) {
            appointments = appointmentRepository.findByPatientId(patientId);
        } else {
            appointments = appointmentRepository.findAll();
        }
        return appointments.stream()
                .map(AppointmentMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(AppointmentMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void updateAppointment(Appointment existingAppointment, UpdateAppointmentRequestDTO updateAppointmentRequest) {
        AppointmentMapper.INSTANCE.updateAppointmentRequestDTOToAppointment(updateAppointmentRequest, existingAppointment);
        appointmentRepository.save(existingAppointment);
    }

}
