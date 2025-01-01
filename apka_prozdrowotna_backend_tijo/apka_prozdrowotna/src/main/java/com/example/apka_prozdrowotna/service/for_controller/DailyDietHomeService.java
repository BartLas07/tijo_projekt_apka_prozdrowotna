package com.example.apka_prozdrowotna.service.for_controller;


import com.example.apka_prozdrowotna.abstract_class.DailyDietHome;
import com.example.apka_prozdrowotna.model.dto.MealIngredientDTO;
import com.example.apka_prozdrowotna.repository.MealIngredientRepository;
import com.example.apka_prozdrowotna.service.meal_perdion.BreakfastService;
import com.example.apka_prozdrowotna.service.meal_perdion.DinnerService;
import com.example.apka_prozdrowotna.service.meal_perdion.LunchService;
import com.example.apka_prozdrowotna.service.meal_perdion.SnacksService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyDietHomeService extends DailyDietHome {

    public DailyDietHomeService(MealIngredientRepository mealIngredientRepository, BreakfastService breakfastService,
    LunchService lunchService, DinnerService dinnerService, SnacksService snacksService) {
        super(mealIngredientRepository, breakfastService, lunchService, dinnerService, snacksService);
    }

    @Override
    public List<MealIngredientDTO> returnIngredientsFromMealPeriod(String mealPeriod) {
        return super.returnIngredientsFromMealPeriod(mealPeriod);
    }

    @Override
    public void deleteIngredientFromMealPeriod(String mealPeriod, Integer ingredientFromMealPeriodId){
        super.deleteIngredientFromMealPeriod(mealPeriod, ingredientFromMealPeriodId);
    }
}
