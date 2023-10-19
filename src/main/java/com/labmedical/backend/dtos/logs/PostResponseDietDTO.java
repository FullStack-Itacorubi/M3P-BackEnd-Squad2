<<<<<<<< HEAD:src/main/java/com/labmedical/backend/dtos/diets/PostResponseDietDTO.java
package com.labmedical.backend.dtos.diets;
========
package com.labmedical.backend.dtos.logs;
>>>>>>>> parent of bafef2e (fix(save-diet): add patient relationship when saving a diet instance and fix exception handlers):src/main/java/com/labmedical/backend/dtos/logs/PostResponseDietDTO.java

import com.labmedical.backend.entities.Diet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record PostResponseDietDTO(

        Long id,
        @NotBlank(message = "Diet Name is required")
        String dietName,

        @NotNull(message = "Date is required")
        LocalDate date,

        @NotNull(message = "Time is required")
        LocalTime time,

        @NotBlank(message = "Type is required")
        Diet.DietType type,

        @NotBlank(message = "Description is required")
        @Size(min = 1, message = "Description cannot be empty")
        String description,

        @NotNull(message = "System Status is required")
        Boolean systemStatus
) {
}
