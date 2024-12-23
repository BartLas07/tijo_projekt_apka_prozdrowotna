package com.example.apka_prozdrowotna;

import com.example.apka_prozdrowotna.controller.BmiController;
import com.example.apka_prozdrowotna.model.dto.UserDetailsDTO;
import com.example.apka_prozdrowotna.repository.UserDetailsRepository;
import com.example.apka_prozdrowotna.service.UserDetailsService;
import com.example.apka_prozdrowotna.service.for_controller.BmiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BmiControllerIntegrationTests {

    @Autowired
    private BmiController bmiController;

    @Autowired
    private UserDetailsRepository userDetailsRepository;


    @Test
    public void testPostBmiIntegration() {
        // Given
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setWeight(70.0);
        userDetailsDTO.setHeight(1.75);

        // When
        ResponseEntity<?> response = bmiController.postBmi(userDetailsDTO);

        // Then
        assertEquals(200, response.getStatusCodeValue(), "HTTP status should be 200 OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(22.86, response.getBody(), "BMI value should be 22.86");

        // Verify that the BMI was saved to the database
        assertTrue(userDetailsRepository.findAllByBmiNotNull().stream()
                        .anyMatch(userDetails -> userDetails.getBmi().equals(22.86)),
                "BMI should be saved to the database correctly");
    }
}
