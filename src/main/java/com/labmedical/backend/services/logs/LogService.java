package com.labmedical.backend.services.logs;

import com.labmedical.backend.dtos.logs.ResponseLogDTO;
import com.labmedical.backend.entities.Log;
import com.labmedical.backend.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LogService {

    void saveLog(Log log);

    List<ResponseLogDTO> getLogs();

    void logUserRecord(String userName, String adminName, String action);

    String logPatientRecord(String userName, String patientName, String action);

    String logEntityRecord(String userName, String patientName, String entityName);

}
