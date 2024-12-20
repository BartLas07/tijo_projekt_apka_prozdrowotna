package com.example.apka_prozdrowotna;

import com.example.apka_prozdrowotna.model.json.MealIngredientSearchedOptionResponseJSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SearchIngredientControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Test integracyjny: /getIngredientList - powinien zwrócić listę składników z bazy")
    void integrationTestGetIngredientList() throws Exception {
        //Given
        //Nic nie podaję, ponieważ  metoda przeszukuje odpowiednio bazę danych bez wcześniejszych danych wejściowych
        //When
        mockMvc.perform(get("/getIngredientList"))
                //Then
                .andExpect(status().isOk())
                // Pierwszy element listyto Agrest
                .andExpect(jsonPath("$[0].label").value("Agrest"))
                .andExpect(jsonPath("$[0].value").value("Agrest"))
                // Drugi element to listy Ananas
                .andExpect(jsonPath("$[1].label").value("Ananas"))
                .andExpect(jsonPath("$[1].value").value("Ananas"));
    }

    @Test
    @DisplayName("Test integracyjny: /postIngredientToMealPeriod/{mealPeriod} - powinien zwrócić status 200 i label")
    void integrationTestPostIngredientToMealPeriod() throws Exception {
        // Given
        MealIngredientSearchedOptionResponseJSON request = new MealIngredientSearchedOptionResponseJSON();
        request.setLabel("Ananas");
        request.setValue("Ananas");
        request.setMealIngredientQuantityInGrams(20);
        //When
        mockMvc.perform(post("/postIngredientToMealPeriod/breakfast")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().string("Ananas"));
    }
}
