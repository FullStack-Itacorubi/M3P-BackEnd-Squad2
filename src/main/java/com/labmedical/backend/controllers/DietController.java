package com.labmedical.backend.controllers;

import com.labmedical.backend.services.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("diets")
public class DietController {

    @Autowired
    private DietService dietService;
}
