package com.example.apka_prozdrowotna.service.for_controller;

import com.example.apka_prozdrowotna.abstract_class.SearchIngredient;
import com.example.apka_prozdrowotna.model.json.MealIngredientSearchedOptionResponseJSON;
import com.example.apka_prozdrowotna.repository.MealIngredientRepository;
import com.example.apka_prozdrowotna.service.meal_perdion.BreakfastService;
import com.example.apka_prozdrowotna.service.meal_perdion.DinnerService;
import com.example.apka_prozdrowotna.service.meal_perdion.LunchService;
import com.example.apka_prozdrowotna.service.meal_perdion.SnacksService;
import org.springframework.stereotype.Service;

@Service
public class SearchIngredientService extends SearchIngredient
{
    public SearchIngredientService(MealIngredientRepository mealIngredientRepository, BreakfastService breakfastService,
                                   LunchService lunchService, DinnerService dinnerService, SnacksService snacksService) {
        super(mealIngredientRepository, breakfastService, lunchService, dinnerService, snacksService);}

    @Override
    public void postIngredientToMealPeriod(String mealPeriod,  MealIngredientSearchedOptionResponseJSON mealIngredientResponse){
        super.postIngredientToMealPeriod(mealPeriod, mealIngredientResponse);
    };

}
