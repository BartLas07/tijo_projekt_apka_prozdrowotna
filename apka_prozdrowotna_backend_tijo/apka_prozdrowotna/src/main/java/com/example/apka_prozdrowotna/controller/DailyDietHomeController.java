package com.example.apka_prozdrowotna.controller;

import com.example.apka_prozdrowotna.model.dto.MealIngredientDTO;
import com.example.apka_prozdrowotna.repository.MealIngredientRepository;
import com.example.apka_prozdrowotna.service.for_controller.DailyDietHomeService;
import com.example.apka_prozdrowotna.service.meal_perdion.BreakfastService;
import com.example.apka_prozdrowotna.service.meal_perdion.DinnerService;
import com.example.apka_prozdrowotna.service.meal_perdion.LunchService;
import com.example.apka_prozdrowotna.service.meal_perdion.SnacksService;
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

    /*
    @ResponseBody
    @GetMapping("/getBreakfastIngradients")
    public ResponseEntity<List<MealIngredientDTO>> getBreakfastIngradients() {
        try {
            List<MealIngredientDTO> ingredients = mealIngredientRepository.findAllByBreakfastIdsWithDuplicates();
            return ResponseEntity.ok(ingredients);
        } catch (Exception e) {
            log.error("Error fetching breakfast ingredients: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @ResponseBody
    @GetMapping("/getLunchIngredients")
    public ResponseEntity<List<MealIngredientDTO>> getLunchIngredients() {
        try {
            List<MealIngredientDTO> ingredients = mealIngredientRepository.findAllByLunchIdsWithDuplicates();
            return ResponseEntity.ok(ingredients);
        } catch (Exception e) {
            log.error("Error fetching lunch ingredients: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseBody
    @GetMapping("/getDinnerIngradients")
    public ResponseEntity<List<MealIngredientDTO>> getDinnerIngradients() {
        try {
            List<MealIngredientDTO> ingredients = mealIngredientRepository.findAllByDinnerIdsWithDuplicates();
            return ResponseEntity.ok(ingredients);
        } catch (Exception e) {
            log.error("Error fetching dinner ingredients: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseBody
    @GetMapping("/getSnacksIngradients")
    public ResponseEntity<List<MealIngredientDTO>> getSnacksIngradients() {
        try {
            List<MealIngredientDTO> ingredients = mealIngredientRepository.findAllBySnacksIdsWithDuplicates();
            return ResponseEntity.ok(ingredients);
        } catch (Exception e) {
            log.error("Error fetching snacks ingredients: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/breakfast/{mealPeriodIngredientId}")
    public ResponseEntity<?> deleteBreakfastIngredient(@PathVariable Long mealPeriodIngredientId) {
        try {
            Integer ingredientId = mealPeriodIngredientId.intValue();
            breakfastService.deleteBreakfast(ingredientId);

            log.info("Ingredient with ID {} was successfully deleted.", ingredientId);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            log.error("Error converting mealIngredientId to integer: ", e);
            return ResponseEntity.badRequest().body("Invalid ingredient ID format.");
        } catch (Exception e) {
            log.error("Error deleting ingredient with ID {}: ", mealPeriodIngredientId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the ingredient.");
        }
    }

    @DeleteMapping("/dinner/{mealPeriodIngredientId}")
    public ResponseEntity<?> deleteDinnerIngredient(@PathVariable Long mealPeriodIngredientId) {
        try {
            Integer ingredientId = mealPeriodIngredientId.intValue();
            dinnerService.deleteDinner(ingredientId);

            log.info("Ingredient with ID {} was successfully deleted.", ingredientId);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            log.error("Error converting mealIngredientId to integer: ", e);
            return ResponseEntity.badRequest().body("Invalid ingredient ID format.");
        } catch (Exception e) {
            log.error("Error deleting ingredient with ID {}: ", mealPeriodIngredientId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the ingredient.");
        }
    }

    @DeleteMapping("/lunch/{mealPeriodIngredientId}")
    public ResponseEntity<?> deleteLunchIngredient(@PathVariable Long mealPeriodIngredientId) {
        try {
            Integer ingredientId = mealPeriodIngredientId.intValue();
            lunchService.deleteLunch(ingredientId);

            log.info("Ingredient with ID {} was successfully deleted.", ingredientId);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            log.error("Error converting mealIngredientId to integer: ", e);
            return ResponseEntity.badRequest().body("Invalid ingredient ID format.");
        } catch (Exception e) {
            log.error("Error deleting ingredient with ID {}: ", mealPeriodIngredientId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the ingredient.");
        }
    }

    @DeleteMapping("/snacks/{mealPeriodIngredientId}")
    public ResponseEntity<?> deleteSnacksIngredient(@PathVariable Long mealPeriodIngredientId) {
        try {
            Integer ingredientId = mealPeriodIngredientId.intValue();
            snacksService.deleteSnacks(ingredientId);

            log.info("Ingredient with ID {} was successfully deleted.", ingredientId);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            log.error("Error converting mealIngredientId to integer: ", e);
            return ResponseEntity.badRequest().body("Invalid ingredient ID format.");
        } catch (Exception e) {
            log.error("Error deleting ingredient with ID {}: ", mealPeriodIngredientId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the ingredient.");
        }
    }
    @ResponseBody
    @GetMapping("/getSumAllIngredientsDinner2")
    public ResponseEntity<MealIngredientDTO> getSumAllIngredientsDinner() {
        try {
            MealIngredientDTO ingredients = dinnerService.getSumAllDinnerIngredients();
            return ResponseEntity.ok(ingredients);
        } catch (Exception e) {
            log.error("Error fetching dinner ingredients: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

 */
}

