package com.labmedical.backend.dtos.logs;

import java.time.LocalDate;

public record ResponseLogDTO(Long id, String description, LocalDate createdAt) {

}
