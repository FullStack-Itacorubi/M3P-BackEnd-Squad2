package com.labmedical.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labmedical.backend.dtos.logs.ResponseLogDTO;
import com.labmedical.backend.entities.Log;
import com.labmedical.backend.repositories.LogRepository;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void saveLog(Log log) {
        this.logRepository.save(log);
    }

    public List<ResponseLogDTO> getLogs() {
        return this.logRepository.findAllByOrderByCreatedAtDesc().stream().map(ResponseLogDTO::new).toList();
    }
}
