package com.labmedical.backend.dtos.logs;

import java.time.LocalDateTime;

import com.labmedical.backend.entities.Log;

public record ResponseLogDTO(Long id, String description, LocalDateTime createdAt) {

    public ResponseLogDTO(Log log) {
        this(log.getId(), log.getDescription(), log.getCreatedAt());
    }
}
