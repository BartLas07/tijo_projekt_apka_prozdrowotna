package com.example.apka_prozdrowotna;

import com.example.apka_prozdrowotna.controller.CaloriePoolController;
import com.example.apka_prozdrowotna.model.dto.UserDetailsDTO;
import com.example.apka_prozdrowotna.repository.UserDetailsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CaloriePoolControllerIntegrationTests {

    @Autowired
    private CaloriePoolController caloriePoolController;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Test
    public void testPostCaloriePoolIntegration() {
        // Given
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setGender("female");
        userDetailsDTO.setAge(30);
        userDetailsDTO.setWeight(70.0);
        userDetailsDTO.setHeight(1.75);
        userDetailsDTO.setActivityLevel("high");
        userDetailsDTO.setWeightPreference("reduction");

        // When
        ResponseEntity<?> response = caloriePoolController.postCaloriePool(userDetailsDTO);

        // Then
        assertEquals(200, response.getStatusCodeValue(), "HTTP status should be 200 OK");
        assertNotNull(response.getBody(), "Response body should not be null");

        assertTrue(response.getBody() instanceof Integer, "The returned value should be of type Integer");
        Integer returnedCaloriePool = (Integer) response.getBody();

        assertTrue(returnedCaloriePool > 0, "Calorie pool should be greater than 0");

        boolean isCaloriePoolSaved = userDetailsRepository.findAllByCaloriePoolNotNull().stream()
                .anyMatch(userDetails -> userDetails.getCaloriePool().equals(returnedCaloriePool));
        assertTrue(isCaloriePoolSaved, "The calorie pool value should be correctly saved in the database");

    }
}
