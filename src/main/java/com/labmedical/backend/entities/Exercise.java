package com.labmedical.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "tb_exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    LocalDate date;

    LocalTime time;





    public enum Type {
        AEROBIC_RESISTANCE,
        MUSCULAR_RESISTANCE,
        FLEXIBILITY,
        STRENGTH,
        AGILITY,
        OTHER
    }
}
