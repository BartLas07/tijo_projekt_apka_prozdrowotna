package com.example.apka_prozdrowotna;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.apka_prozdrowotna.service.for_controller.BmiService;
import org.junit.jupiter.api.Test;

public class BmiControllerUnitTests {

    @Test
    public void testCalculateBmi() {
        // Given
        BmiService bmiService = new BmiService();
        double weight = 70.0;
        double height = 1.75;
        double expectedBmi = 22.86;

        // When
        double actualBmi = bmiService.calculateBmi(weight, height);

        // Then
        assertEquals(expectedBmi, actualBmi, "BMI calculation should be 22.86");
    }

    @Test
    public void testCalculateBmiForDifferentValues() {
        // Given
        BmiService bmiService = new BmiService();
        double weight = 90.0; // waga w kilogramach
        double height = 1.80; // wzrost w metrach
        double expectedBmi = 27.78;

        // When
        double actualBmi = bmiService.calculateBmi(weight, height);

        // Then
        assertEquals(expectedBmi, actualBmi, "BMI calculation should be 27.78");
    }
}
