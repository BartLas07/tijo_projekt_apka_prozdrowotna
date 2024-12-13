package com.example.apka_prozdrowotna.controller;

import com.example.apka_prozdrowotna.model.*;
import com.example.apka_prozdrowotna.model.json.IngradientsOptionJSON;
import com.example.apka_prozdrowotna.model.json.IngradientsOptionResponseJSON;
import com.example.apka_prozdrowotna.repository.MealIngredientRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin("*")
@Controller
@RequiredArgsConstructor
public class SearchIngredientController {

    private final MealIngredientRepository mealIngredientRepository;
    private final BreakfastService breakfastService;
    private  final LunchService lunchService;
    private final DinnerService dinnerService;
    private final SnacksService snacksService;



    @ResponseBody
    @GetMapping("/getIngradientList")
    public ResponseEntity<List<IngradientsOptionJSON>> getIngredientList() {
        try {
            List<IngradientsOptionJSON> ingradientsOptionJSON = new ArrayList<>();
            List<String> ingradientsCollection = mealIngredientRepository.findAll().stream()
                    .map(MealIngredient::getIngredient)
                    .collect(Collectors.toList());

            ingradientsCollection.forEach(ingradientName -> {
                ingradientsOptionJSON.add(new IngradientsOptionJSON(ingradientName, ingradientName));
            });

            log.info(ingradientsOptionJSON.toString());
            return ResponseEntity.ok(ingradientsOptionJSON);
        } catch (Exception e) {
            log.error("Error fetching ingredient list", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }





    @PostMapping("/postBreakfastIngredients")
    public ResponseEntity<?> postBreakfastIngredients(@RequestBody IngradientsOptionResponseJSON ingredient) {
        try {
            Integer ingredientId = mealIngredientRepository
                    .findByIngredient(ingredient.getLabel())
                    .getMeal_ingredient_id();
            breakfastService.saveBreakfast(ingredientId, ingredient.getQuantity_of_grams());
            return ResponseEntity.ok(ingredient);
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
}
