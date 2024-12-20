package com.example.apka_prozdrowotna.controller;

import com.example.apka_prozdrowotna.model.*;
import com.example.apka_prozdrowotna.model.json.IngradientsOptionJSON;
import com.example.apka_prozdrowotna.model.json.MealIngredientSearchedOptionResponseJSON;
import com.example.apka_prozdrowotna.repository.MealIngredientRepository;
import com.example.apka_prozdrowotna.service.for_controller.SearchIngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin("*")
@Controller
@RequiredArgsConstructor
public class SearchIngredientController {

    private final SearchIngredientService searchIngredientService;
    private final MealIngredientRepository mealIngredientRepository;


    @ResponseBody
    @GetMapping("/getIngredientList")
    public ResponseEntity<List<IngradientsOptionJSON>> getIngredientList() {
        try {
            List<IngradientsOptionJSON> ingradientsOptionJSONList = new ArrayList<>();
            List<String> ingradientsCollection = mealIngredientRepository.findAll().stream()
                    .map(MealIngredient::getMealIngredientName)
                    .collect(Collectors.toList());

            ingradientsCollection.forEach(ingradientName -> {
                ingradientsOptionJSONList.add(new IngradientsOptionJSON(ingradientName, ingradientName));
            });

            return ResponseEntity.ok(ingradientsOptionJSONList);
        } catch (Exception e) {
            log.error("Error fetching ingredient list", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PostMapping("/postIngredientToMealPeriod/{mealPeriod}")
    public ResponseEntity<?> postIngredientToMealPeriod(@PathVariable String mealPeriod,
                                                        @RequestBody MealIngredientSearchedOptionResponseJSON mealIngredientResponse) {
        try {
            searchIngredientService.postIngredientToMealPeriod(mealPeriod, mealIngredientResponse);
            return ResponseEntity.ok(mealIngredientResponse.getLabel());
        } catch (Exception e) {
            log.error("Invalid JSON data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON data");
        }
    }
/*
    @PostMapping("/postBreakfastIngredients")
    public ResponseEntity<?> postBreakfastIngredients(@RequestBody IngradientsOptionResponseJSON ingredient) {
        try {
            Integer ingredientId = mealIngredientRepository
                    .findByIngredient(ingredient.getLabel())
                    .getMeal_ingredient_id();
            Breakfast breakfast = breakfastService.saveBreakfast(ingredientId, ingredient.getQuantity_of_grams());
            return ResponseEntity.ok(breakfast);
        } catch (Exception e) {
            log.error("Invalid JSON data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON data");
        }
    }

    @PostMapping("/postLunchIngredients")
    public ResponseEntity<?> postLunchIngredients(@RequestBody IngradientsOptionResponseJSON ingredient) {
        try {
            Integer ingredientId = mealIngredientRepository
                    .findByIngredient(ingredient.getLabel())
                    .getMeal_ingredient_id();
            lunchService.saveLunch(ingredientId, ingredient.getQuantity_of_grams());
            return ResponseEntity.ok(ingredient);
        } catch (Exception e) {
            log.error("Invalid JSON data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON data");
        }
    }


    @PostMapping("/postDinnerIngradients")
    public ResponseEntity<?> postDinnerIngradients(@RequestBody IngradientsOptionResponseJSON ingredient) {
        try {
            Integer ingredientId = mealIngredientRepository
                    .findByIngredient(ingredient.getLabel())
                    .getMeal_ingredient_id();
            dinnerService.saveDinner(ingredientId, ingredient.getQuantity_of_grams());
            return ResponseEntity.ok(ingredient);
        } catch (Exception e) {
            log.error("Invalid JSON data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON data");
        }
    }

    @PostMapping("/postSnacksIngradients")
    public ResponseEntity<?> postSnacksIngradients(@RequestBody IngradientsOptionResponseJSON ingredient) {
        try {
            Integer ingredientId = mealIngredientRepository
                    .findByIngredient(ingredient.getLabel())
                    .getMeal_ingredient_id();
            snacksService.saveSnack(ingredientId, ingredient.getQuantity_of_grams());
            return ResponseEntity.ok(ingredient);
        } catch (Exception e) {
            log.error("Invalid JSON data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON data");
        }
    }

 */
}
