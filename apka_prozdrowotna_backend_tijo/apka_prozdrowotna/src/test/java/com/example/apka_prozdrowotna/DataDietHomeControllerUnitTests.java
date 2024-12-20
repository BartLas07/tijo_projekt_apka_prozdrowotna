package com.example.apka_prozdrowotna;

import com.example.apka_prozdrowotna.controller.DailyDietHomeController;
import com.example.apka_prozdrowotna.model.dto.MealIngredientDTO;
import com.example.apka_prozdrowotna.service.for_controller.DailyDietHomeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataDietHomeControllerUnitTests {

    @Mock
    private DailyDietHomeService dailyDietHomeService;

    @InjectMocks
    private DailyDietHomeController dailyDietHomeController;

    @Test
    @DisplayName("getIngradientsFromMealPeriod - powinien zwrócić składniki dla podanego okresu posiłkowego")
    void shouldReturnIngredientsForMealPeriod() {
        // Given
        String mealPeriod = "breakfast";
        List<MealIngredientDTO> mockIngredients = Arrays.asList(
                new MealIngredientDTO(1,1,"Agrest",10.5,
                        5.0,9.5,4.5,3.5,6.7,8.9,9.0),
                new MealIngredientDTO(2,1,"Ananas",13.5,
                        4.4,7.5,5.5,3.5,6.7,8.9,12.0)
        );

        when(dailyDietHomeService.returnIngredientsFromMealPeriod(mealPeriod)).thenReturn(mockIngredients);

        // When
        ResponseEntity<List<MealIngredientDTO>> response = dailyDietHomeController.getIngradientsFromMealPeriod(mealPeriod);

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody().get(0).getMealIngredientName()).isEqualTo("Agrest");

        verify(dailyDietHomeService, times(1)).returnIngredientsFromMealPeriod(mealPeriod);
    }

    @Test
    @DisplayName("deleteBreakfastIngredient - powinien usunąć składnik z danego okresu posiłkowego")
    void shouldDeleteIngredientFromMealPeriod() {
        // Given
        String mealPeriod = "lunch";
        Integer ingredientFromLunchId = 1;

        doNothing().when(dailyDietHomeService).deleteIngredientFromMealPeriod(mealPeriod, ingredientFromLunchId);

        // When
        ResponseEntity<?> response = dailyDietHomeController.deleteBreakfastIngredient(mealPeriod, ingredientFromLunchId);

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        verify(dailyDietHomeService, times(1)).deleteIngredientFromMealPeriod(mealPeriod, ingredientFromLunchId);
    }
}