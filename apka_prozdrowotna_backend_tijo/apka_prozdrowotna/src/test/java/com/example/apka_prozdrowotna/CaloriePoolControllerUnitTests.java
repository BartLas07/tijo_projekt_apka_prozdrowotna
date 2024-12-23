package com.example.apka_prozdrowotna;

import com.example.apka_prozdrowotna.service.for_controller.CaloriePoolService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CaloriePoolControllerUnitTests {

    private final CaloriePoolService caloriePoolService = new CaloriePoolService();

    @Test
    void shouldCalculateCaloriePoolForFemaleWithModerateActivityAndReduction() {
        // Given
        String gender = "female";
        int age = 30;
        double weight = 65.0;
        double height = 165.0;
        String activityLevel = "moderate";
        String weightPreference = "reduction";

        // Expected
        int expectedCalories = 1807;

        // When
        int actualCalories = caloriePoolService.calculateCaloriePool(gender, age, weight, height, activityLevel, weightPreference);

        // Then
        assertEquals(expectedCalories, actualCalories, "Calories should be 1807 kcal" +
                "for female with moderate activity and reduction preference.");
    }

    @Test
    void shouldCalculateCaloriePoolForMaleWithHighActivityAndGain() {
        // Given
        String gender = "male";
        int age = 25;
        double weight = 75.0;
        double height = 180.0;
        String activityLevel = "high";
        String weightPreference = "gain";

        // Expected
        int expectedCalories = 3743;

        // When
        int actualCalories = caloriePoolService.calculateCaloriePool(gender, age, weight, height, activityLevel, weightPreference);

        // Then
        assertEquals(expectedCalories, actualCalories, "Calories should be 3743 kcal for male with high activity and gain preference.");
    }
}
