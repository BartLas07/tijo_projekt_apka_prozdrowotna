package com.example.apka_prozdrowotna;

import com.example.apka_prozdrowotna.service.for_controller.RecommendedHydrationService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecommendedHydrationControllerUnitTests {

    private final RecommendedHydrationService hydrationService = new RecommendedHydrationService();

    @Test
    void shouldCalculateRecommendedHydrationForNormalWeight() {
        // Given
        double weight = 70.0;

        // Expected
        double expectedHydration = 2.5;

        // When
        double actualHydration = hydrationService.calculateRecommendedHydration(weight);

        // Then
        assertEquals(expectedHydration, actualHydration, "Hydration should be correctly calculated for a normal weight.");
    }

    @Test
    void shouldCalculateRecommendedHydrationForHigherWeight() {
        // Given
        double weight = 90.0;

        // Expected
        double expectedHydration = 3.2;

        // When
        double actualHydration = hydrationService.calculateRecommendedHydration(weight);

        // Then
        assertEquals(expectedHydration, actualHydration, "Hydration should be correctly calculated for a higher weight.");
    }
}
