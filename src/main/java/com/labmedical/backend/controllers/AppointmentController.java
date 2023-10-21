package com.labmedical.backend.controllers;
import com.labmedical.backend.dtos.appointment.AppointmentRequestDTO;
import com.labmedical.backend.dtos.appointment.AppointmentResponseDTO;
import com.labmedical.backend.entities.UsersType;
import com.labmedical.backend.services.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @PostMapping
    public ResponseEntity<Object> createAppointment(@Validated @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        try {
            AppointmentResponseDTO response = appointmentService.createAppointment(appointmentRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF or email already exists.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error: " + ex.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable Long id){
        try {
            boolean appointmentDeleted = appointmentService.deleteAppointment(id);
            if (appointmentDeleted) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Appointment deleted succesfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
            }
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error: " + ex.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAppointmentById(@PathVariable Long id) {
        try {
            Optional<AppointmentResponseDTO> response = appointmentService.getAppointmentById(id);
            if (response.isPresent()) {
                return new ResponseEntity<>(response.get(), HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error: " + ex.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}


