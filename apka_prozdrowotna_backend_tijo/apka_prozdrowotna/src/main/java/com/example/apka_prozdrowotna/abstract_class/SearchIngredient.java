package com.example.apka_prozdrowotna.abstract_class;

import com.example.apka_prozdrowotna.interfaces.ISearchIngredientController;
import com.example.apka_prozdrowotna.model.MealIngredient;
import com.example.apka_prozdrowotna.model.json.MealIngredientSearchedOptionResponseJSON;
import com.example.apka_prozdrowotna.repository.MealIngredientRepository;
import com.example.apka_prozdrowotna.service.meal_perdion.BreakfastService;
import com.example.apka_prozdrowotna.service.meal_perdion.DinnerService;
import com.example.apka_prozdrowotna.service.meal_perdion.LunchService;
import com.example.apka_prozdrowotna.service.meal_perdion.SnacksService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class SearchIngredient implements ISearchIngredientController {


    private final MealIngredientRepository mealIngredientRepository;
    private final BreakfastService breakfastService;
    private final LunchService lunchService;
    private final DinnerService dinnerService;
    private final SnacksService snacksService;

    @Override
    public void postIngredientToMealPeriod(String mealPeriod, MealIngredientSearchedOptionResponseJSON mealIngredientResponse){
       MealIngredient mealIngredient =  mealIngredientRepository.findByMealIngredientName(mealIngredientResponse.getLabel());

        switch (mealPeriod) {
            case "breakfast":
                breakfastService.saveMealInfredientsForBreakfast(mealIngredient.getMealIngredientId(),mealIngredientResponse.getMealIngredientQuantityInGrams());
                break;
            case "lunch":
                lunchService.saveMealIngredientForLunch(mealIngredient.getMealIngredientId(),mealIngredientResponse.getMealIngredientQuantityInGrams());
                break;
            case "dinner":
                dinnerService.saveMealIngredientForDinner(mealIngredient.getMealIngredientId(),mealIngredientResponse.getMealIngredientQuantityInGrams());
                break;
            case "snacks":
                snacksService.saveMealIngredientForSnack(mealIngredient.getMealIngredientId(),mealIngredientResponse.getMealIngredientQuantityInGrams());
                break;
            default:
                break;
        }
    };
}
