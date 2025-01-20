package com.example.apka_prozdrowotna;


import com.example.apka_prozdrowotna.controller.BmiController;
import com.example.apka_prozdrowotna.controller.CaloriePoolController;
import com.example.apka_prozdrowotna.controller.DailytDietHomeController2;
import com.example.apka_prozdrowotna.controller.RecommendedHydrationController;
import com.example.apka_prozdrowotna.repository.UserDetailsRepository;
import com.example.apka_prozdrowotna.repository.meal_period.LunchRepository;
import com.example.apka_prozdrowotna.service.UserDetailsService;
import com.example.apka_prozdrowotna.service.meal_perdion.BreakfastService;
import com.example.apka_prozdrowotna.service.meal_perdion.LunchService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.apka_prozdrowotna.model.UserDetails;
import com.example.apka_prozdrowotna.model.dto.UserDetailsDTO;
import org.springframework.http.ResponseEntity;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DataDietControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BreakfastService breakfastService;

    @Autowired
    private LunchRepository lunchRepository;


    @Autowired
    private LunchService lunchService;

    @Autowired
    private BmiController bmiController;

    @Autowired
    private CaloriePoolController caloriePoolController;

    @Autowired
    private RecommendedHydrationController recommendedHydrationController;

    @Autowired
    private DailytDietHomeController2 dailytDietHomeController2;


    @Test
    @DisplayName("Powinien usunąć składnik z podanego okresu posiłkowego")
    void shouldDeleteIngredientFromMealPeriod() throws Exception {

        lunchRepository.deleteAll();
        // Given
        //mealIngredientId = 2 ma Ananas
        var lunch = lunchService.saveMealIngredientForLunch(
                2, 40);

        // When
        // Przed usunięciem sprawdzamy, że składnik istnieje
        var resultBefore = mockMvc.perform(get("/getIngredients/lunch")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<Map<String, Object>> ingredientsBefore = objectMapper.readValue(
                resultBefore.getResponse().getContentAsString(),
                new TypeReference<List<Map<String, Object>>>() {}
        );

        assertThat(ingredientsBefore).hasSize(1);
        assertThat(ingredientsBefore.get(0).get("mealIngredientName")).isEqualTo("Ananas");

        // When
        mockMvc.perform(delete("/deleteIngredient/lunch/" + lunch.getMealIngredientForLunchId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Sprawdzenie, czy po usunięciu składnik już nie istnieje
        var resultAfter = mockMvc.perform(get("/getIngredients/lunch")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<Map<String, Object>> ingredientsAfter = objectMapper.readValue(
                resultAfter.getResponse().getContentAsString(),
                new TypeReference<List<Map<String, Object>>>() {}
        );

        assertThat(ingredientsAfter).isEmpty();
    }

    @Test
    @DisplayName("Powinien zwrócić składniki dla podanego okresu posiłkowego")
    void shouldReturnIngredientsForMealPeriod() throws Exception {
        // Given
       //mealIngredientId = 1 ma Agrest

        breakfastService.saveMealInfredientsForBreakfast(
                1, 30);

        // When
        var result = mockMvc.perform(get("/getIngredients/breakfast")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Parse response
        List<Map<String, Object>> ingredients = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<Map<String, Object>>>() {}
        );
        // Then
        assertThat(ingredients).isNotNull();
        assertThat(ingredients.size()).isEqualTo(1);
        assertThat(ingredients.get(0).get("mealIngredientName")).isEqualTo("Agrest");
        assertThat(ingredients.get(0).get("calories")).isEqualTo(13.2);
    }
    @Test
    public void testGetBmiIntegration() {
        // Given
        // Prepare test data for BMI (Weight and Height)
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setWeight(70.0);
        userDetailsDTO.setHeight(1.75);

        // When - POST /api/postBmi
        ResponseEntity<?> postBmiResponse = bmiController.postBmi(userDetailsDTO);

        // Then - verify POST response
        assertEquals(200, postBmiResponse.getStatusCodeValue(),
                "POST /api/postBmi should return 200 OK");
        assertNotNull(postBmiResponse.getBody(),
                "POST /api/postBmi response body should not be null");

        // When - GET /getBmi
        ResponseEntity<UserDetails> getBmiResponse = dailytDietHomeController2.getBmi();

        // Then - verify GET response
        assertEquals(200, getBmiResponse.getStatusCodeValue(),
                "GET /getBmi should return 200 OK");
        assertNotNull(getBmiResponse.getBody(),
                "GET /getBmi response body should not be null");

        UserDetails userDetails = getBmiResponse.getBody();
        assertNotNull(userDetails.getBmi(), "UserDetails should have a non-null BMI");
        assertTrue(userDetails.getBmi() > 0, "BMI should be a positive number");
    }

    @Test
    public void testGetCaloriePoolIntegration() {
        // Given
        // Prepare test data for Calorie Pool (Gender, Age, Weight, etc.)
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setGender("male");
        userDetailsDTO.setAge(25);
        userDetailsDTO.setWeight(80.0);
        userDetailsDTO.setHeight(1.80);
        userDetailsDTO.setActivityLevel("low");
        userDetailsDTO.setWeightPreference("reduction");

        // When - POST /api/postCaloriePool
        ResponseEntity<?> postCaloriePoolResponse = caloriePoolController.postCaloriePool(userDetailsDTO);

        // Then - verify POST response
        assertEquals(200, postCaloriePoolResponse.getStatusCodeValue(),
                "POST /api/postCaloriePool should return 200 OK");
        assertNotNull(postCaloriePoolResponse.getBody(),
                "POST /api/postCaloriePool response body should not be null");

        // When - GET /getCaloriePool
        ResponseEntity<UserDetails> getCaloriePoolResponse = dailytDietHomeController2.getCaloriePool();

        // Then - verify GET response
        assertEquals(200, getCaloriePoolResponse.getStatusCodeValue(),
                "GET /getCaloriePool should return 200 OK");
        assertNotNull(getCaloriePoolResponse.getBody(),
                "GET /getCaloriePool response body should not be null");

        UserDetails userDetails = getCaloriePoolResponse.getBody();
        assertNotNull(userDetails.getCaloriePool(), "UserDetails should have a non-null caloriePool");
        assertTrue(userDetails.getCaloriePool() > 0, "Calorie pool should be a positive number");
    }

    @Test
    public void testGetRecommendedHydrationIntegration() {
        // Given
        // Prepare test data for Recommended Hydration (Weight)
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setWeight(65.0);  // Example weight

        // When - POST /api/postRecommendedHydration
        ResponseEntity<?> postHydrationResponse = recommendedHydrationController.postCaloriePool(userDetailsDTO);

        // Then - verify POST response
        assertEquals(200, postHydrationResponse.getStatusCodeValue(),
                "POST /api/postRecommendedHydration should return 200 OK");
        assertNotNull(postHydrationResponse.getBody(),
                "POST /api/postRecommendedHydration response body should not be null");

        // Convert the returned value (String) to Double
        Double postedHydration = Double.valueOf((String) postHydrationResponse.getBody());
        assertTrue(postedHydration > 0, "Recommended hydration should be a positive value");

        // When - GET /getRecommendedHydration
        ResponseEntity<UserDetails> getHydrationResponse = dailytDietHomeController2.getRecommendedHydration();

        // Then - verify GET response
        assertEquals(200, getHydrationResponse.getStatusCodeValue(),
                "GET /getRecommendedHydration should return 200 OK");
        assertNotNull(getHydrationResponse.getBody(),
                "GET /getRecommendedHydration response body should not be null");

        UserDetails userDetails = getHydrationResponse.getBody();
        assertNotNull(userDetails.getRecommendedHydration(),
                "UserDetails should have a non-null recommendedHydration");
        assertEquals(postedHydration, userDetails.getRecommendedHydration(),
                "The retrieved recommendedHydration should match the posted value");
    }

}

