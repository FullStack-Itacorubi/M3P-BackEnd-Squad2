package com.labmedical.backend.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private String cpf;
    private String phone;
    private String email;
    private String status;
}
