package com.labmedical.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labmedical.backend.services.LogService;

@RestController
@RequestMapping("logs")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping
    private ResponseEntity<?> listLogs() {
        return ResponseEntity.ok(this.logService.getLogs());
    }
}
