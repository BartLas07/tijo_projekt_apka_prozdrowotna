package com.example.apka_prozdrowotna.service.for_controller;

import org.springframework.stereotype.Service;

@Service
public class RecommendedHydrationService {

    public Double calculateRecommendedHydration(Double weight) {
        return Math.round((weight * 0.035) * 10.0)/10.0;
    }
}
