package com.example.apka_prozdrowotna.interfaces;

import com.example.apka_prozdrowotna.model.json.MealIngredientSearchedOptionResponseJSON;


public interface ISearchIngredientController {
    void postIngredientToMealPeriod(String mealPeriod,  MealIngredientSearchedOptionResponseJSON mealIngredient);
}
