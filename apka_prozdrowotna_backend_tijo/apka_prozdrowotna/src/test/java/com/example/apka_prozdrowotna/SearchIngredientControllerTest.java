package com.example.apka_prozdrowotna;

import com.example.apka_prozdrowotna.model.MealIngredient;
import com.example.apka_prozdrowotna.repository.MealIngredientRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class SearchIngredientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MealIngredientRepository mealIngredientRepository;

    // Given
    @BeforeAll
    void setupDatabase() {
        mealIngredientRepository.deleteAll();
        mealIngredientRepository.save(new MealIngredient("Tomatoo"));
        mealIngredientRepository.save(new MealIngredient("Cheese"));
        mealIngredientRepository.save(new MealIngredient("Basil"));
    }

    @AfterAll
    void cleanupDatabase() {
        mealIngredientRepository.deleteAll();
    }

    @Test
    void testGetIngredientList() throws Exception {

        // When
        mockMvc.perform(get("/getIngradientList"))

                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].value", is("Tomatoo")))
                .andExpect(jsonPath("$[0].label", is("Tomato")))
                .andExpect(jsonPath("$[1].value", is("Cheese")))
                .andExpect(jsonPath("$[1].label", is("Cheese")))
                .andExpect(jsonPath("$[2].value", is("Basil")))
                .andExpect(jsonPath("$[2].label", is("Basil")));
    }
}
