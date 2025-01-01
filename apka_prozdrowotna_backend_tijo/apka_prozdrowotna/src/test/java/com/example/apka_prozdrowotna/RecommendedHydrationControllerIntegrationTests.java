package com.example.apka_prozdrowotna;

import com.example.apka_prozdrowotna.controller.RecommendedHydrationController;
import com.example.apka_prozdrowotna.model.dto.UserDetailsDTO;
import com.example.apka_prozdrowotna.repository.UserDetailsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RecommendedHydrationControllerIntegrationTests {

    @Autowired
    private RecommendedHydrationController recommendedHydrationController;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Test
    public void testPostRecommendedHydrationIntegration() {
        // Given
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setWeight(70.0); 

        // When
        ResponseEntity<?> response = recommendedHydrationController.postCaloriePool(userDetailsDTO);

        // Then
        assertEquals(200, response.getStatusCodeValue(), "HTTP status should be 200 OK");
        assertNotNull(response.getBody(), "Response body should not be null");

        assertTrue(response.getBody() instanceof String, "The returned value should be of type String");

        Double returnedHydration = Double.valueOf((String) response.getBody());
        assertTrue(returnedHydration > 0, "Recommended hydration should be greater than 0");
        boolean isHydrationSaved = userDetailsRepository.findAllByRecommendedHydrationNotNull().stream()
                .anyMatch(userDetails -> userDetails.getRecommendedHydration().equals(returnedHydration));
        assertTrue(isHydrationSaved, "The recommended hydration value should be correctly saved in the database");
    }
}
