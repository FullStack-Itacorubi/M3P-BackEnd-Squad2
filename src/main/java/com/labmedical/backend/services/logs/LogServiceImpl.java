package com.labmedical.backend.services.logs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labmedical.backend.dtos.logs.ResponseLogDTO;
import com.labmedical.backend.entities.Log;
import com.labmedical.backend.repositories.LogRepository;

@Service
public class LogServiceImpl implements LogService{

    @Autowired
    private LogRepository logRepository;

    public void saveLog(Log log) {
        this.logRepository.save(log);
    }

    public List<ResponseLogDTO> getLogs() {
        return this.logRepository.findAllByOrderByCreatedAtDesc().stream().map(ResponseLogDTO::new).toList();
    }

    public void logUserRecord(String userName, String adminName, String action){
        String logDescription = "O usuário " + userName + "foi " + action + " pelo usuário " + adminName;
        Log log = new Log();
        log.setDescription(logDescription);
        this.logRepository.save(log);
    }
    public String logPatientRecord(String userName, String patientName, String action){
        return "O paciente " + patientName + "foi " + action + " pelo usuário " + userName;
    }
     public String logEntityRecord(String userName, String patientName, String entityName){
        return "O usuário " + userName + " registrou" + userName;
    }

}
