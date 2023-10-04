package com.labmedical.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labmedical.backend.entities.Log;
import com.labmedical.backend.repositories.LogRepository;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void saveLog(Log log) {
        this.logRepository.save(log);
    }
}
