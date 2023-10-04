package com.labmedical.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labmedical.backend.entities.Log;

@Repository
public interface LogRepository extends JpaRepository<Long, Log> {

}
