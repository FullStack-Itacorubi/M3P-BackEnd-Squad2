package com.labmedical.backend.entities;
import com.labmedical.backend.entities.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users extends Person {

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "System Status is required")
    @Enumerated(EnumType.STRING)
    private UserType type;

    @NotNull(message = "System Status is required")
    @Column(name = "system_status")
    private Boolean systemStatus = true;

}

