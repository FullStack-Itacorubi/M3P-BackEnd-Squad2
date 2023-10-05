package com.labmedical.backend.controllers;

import com.labmedical.backend.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;
}
