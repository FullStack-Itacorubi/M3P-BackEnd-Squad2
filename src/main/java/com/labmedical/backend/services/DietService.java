package com.labmedical.backend.services;

import com.labmedical.backend.repositories.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietService {

    @Autowired
    private DietRepository dietRepository;
}
