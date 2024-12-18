package com.example.apka_prozdrowotna.interfaces;

import com.example.apka_prozdrowotna.model.dto.MealIngredientDTO;

import java.util.List;

public interface IDailyDietHome {
     List<MealIngredientDTO> returnIngredientsFromMealPeriod(String mealPeriod);
     void deleteIngredientFromMealPeriod(String mealPeriod, Integer ingredientFromMealPeriodId);
}
