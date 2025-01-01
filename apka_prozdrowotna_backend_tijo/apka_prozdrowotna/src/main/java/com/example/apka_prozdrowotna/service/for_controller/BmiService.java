package com.example.apka_prozdrowotna.service.for_controller;

import org.springframework.stereotype.Service;

@Service
public class BmiService {

    public Double calculateBmi(Double weight, Double height) {
        return Math.round((weight / (height * height)) * 100.0) / 100.0;
    }

}
