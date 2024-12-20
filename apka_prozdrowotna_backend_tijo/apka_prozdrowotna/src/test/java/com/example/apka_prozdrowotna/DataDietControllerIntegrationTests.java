package com.example.apka_prozdrowotna;

import com.example.apka_prozdrowotna.model.MealIngredient;
import com.example.apka_prozdrowotna.repository.MealIngredientRepository;
import com.example.apka_prozdrowotna.repository.meal_period.BreakfastRepository;
import com.example.apka_prozdrowotna.repository.meal_period.LunchRepository;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    private BreakfastRepository breakfastRepository;

    @Autowired
    private LunchService lunchService;

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


}
