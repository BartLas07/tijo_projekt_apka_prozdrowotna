package com.example.apka_prozdrowotna.service.for_controller;

import org.springframework.stereotype.Service;

@Service
public class CaloriePoolService {


    public Integer calculateCaloriePool(String gender, Integer age, Double weight, Double height, String activityLevel, String weightPreference) {

        double physicalActivityCoefficient;
        double caloriePool;


        if ("low".equals(activityLevel)) {
            physicalActivityCoefficient = 1.3;
        } else if ("moderate".equals(activityLevel)) {
            physicalActivityCoefficient = 1.6;
        } else if ("high".equals(activityLevel)) {
            physicalActivityCoefficient = 1.8;
        } else {
            throw new IllegalArgumentException("Invalid physical activity level");
        }

        if ("female".equals(gender)) {
            caloriePool = 655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age);
        } else if ("male".equals(gender)) {
            caloriePool = 66.5 + (13.75 * weight) + (5.003 * height) - (6.775 * age);
        } else {
            throw new IllegalArgumentException("Invalid gender");
        }

        caloriePool *= physicalActivityCoefficient;


        switch (weightPreference) {
            case "reduction":
                caloriePool -= 500;
                break;
            case "gain":
                caloriePool += 450;
                break;
            case "stabilization":
                break;
            default:
                throw new IllegalArgumentException("Invalid weight preference");
        }

        return (int) Math.ceil(caloriePool);
    }


}
