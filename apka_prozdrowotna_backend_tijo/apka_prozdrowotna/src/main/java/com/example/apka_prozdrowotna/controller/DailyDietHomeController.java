package com.example.apka_prozdrowotna.controller;

import com.example.apka_prozdrowotna.model.dto.MealIngredientDTO;

import com.example.apka_prozdrowotna.service.for_controller.DailyDietHomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@Controller
@RequiredArgsConstructor
public class DailyDietHomeController {

    private final DailyDietHomeService dailyDietHomeService;

    @ResponseBody
    @GetMapping("/getIngredients/{mealPeriod}")
    public ResponseEntity<List<MealIngredientDTO>> getIngradientsFromMealPeriod(@PathVariable("mealPeriod") String mealPeriod) {
        try {
            List<MealIngredientDTO> ingredients = dailyDietHomeService.returnIngredientsFromMealPeriod(mealPeriod);
            return ResponseEntity.ok(ingredients);
        } catch (Exception e) {
            log.error("Error fetching ingredients for '{}': ", mealPeriod, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @DeleteMapping("/deleteIngredient/{mealPeriod}/{ingredientFromMealPeriodId}")
    public ResponseEntity<?> deleteBreakfastIngredient(@PathVariable String mealPeriod,@PathVariable Integer ingredientFromMealPeriodId) {
        try {
            dailyDietHomeService.deleteIngredientFromMealPeriod(mealPeriod, ingredientFromMealPeriodId);
            log.info("Ingredient with ID {} was successfully deleted from {}.", ingredientFromMealPeriodId, mealPeriod);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error deleting ingredient with ID {} from {}: ", ingredientFromMealPeriodId, mealPeriod, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the ingredient.");
        }
    }


}

