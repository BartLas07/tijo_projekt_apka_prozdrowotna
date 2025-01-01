package com.example.apka_prozdrowotna;

import com.example.apka_prozdrowotna.controller.SearchIngredientController;
import com.example.apka_prozdrowotna.model.MealIngredient;
import com.example.apka_prozdrowotna.model.json.IngradientsOptionJSON;
import com.example.apka_prozdrowotna.model.json.MealIngredientSearchedOptionResponseJSON;
import com.example.apka_prozdrowotna.repository.MealIngredientRepository;
import com.example.apka_prozdrowotna.service.for_controller.SearchIngredientService;
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
public class SearchIngredientControllerUnitTests {

    @Mock
    private MealIngredientRepository mealIngredientRepository;

    @Mock
    private SearchIngredientService searchIngredientService;

    @InjectMocks
    private SearchIngredientController searchIngredientController;

    @Test
    @DisplayName("getIngredientList() - powinien zwrócić listę składników z repozytorium")
    void shouldReturnListOfIngredientsFromRepository() {
        // Given
        MealIngredient m1 = new MealIngredient();
        m1.setMealIngredientName("Marchew");
        MealIngredient m2 = new MealIngredient();
        m2.setMealIngredientName("Ogórek");

        when(mealIngredientRepository.findAll()).thenReturn(Arrays.asList(m1, m2));

        // When
        ResponseEntity<List<IngradientsOptionJSON>> response = searchIngredientController.getIngredientList();

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        List<IngradientsOptionJSON> body = response.getBody();
        assertThat(body).hasSize(2);
        assertThat(body.get(0).getValue()).isEqualTo("Marchew");
        assertThat(body.get(0).getLabel()).isEqualTo("Marchew");
        assertThat(body.get(1).getValue()).isEqualTo("Ogórek");
        assertThat(body.get(1).getLabel()).isEqualTo("Ogórek");

        verify(mealIngredientRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("postIngredientToMealPeriod - powinien wywołać service i zwrócić label")
    void shouldPostIngredientToMealPeriodAndReturnLabel() {
        // Given
        MealIngredientSearchedOptionResponseJSON request = new MealIngredientSearchedOptionResponseJSON();
        request.setLabel("Ananas");
        request.setValue("Ananas");
        request.setMealIngredientQuantityInGrams(20);

        doNothing().when(searchIngredientService).postIngredientToMealPeriod("breakfast", request);

        // When
        ResponseEntity<?> response = searchIngredientController.postIngredientToMealPeriod("breakfast", request);

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("Ananas");

        verify(searchIngredientService, times(1)).postIngredientToMealPeriod("breakfast", request);
    }
}
