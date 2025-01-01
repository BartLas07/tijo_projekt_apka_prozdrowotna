package com.example.apka_prozdrowotna.abstract_class;

import com.example.apka_prozdrowotna.interfaces.IDailyDietHome;
import com.example.apka_prozdrowotna.model.dto.MealIngredientDTO;
import com.example.apka_prozdrowotna.repository.MealIngredientRepository;
import com.example.apka_prozdrowotna.service.meal_perdion.BreakfastService;
import com.example.apka_prozdrowotna.service.meal_perdion.DinnerService;
import com.example.apka_prozdrowotna.service.meal_perdion.LunchService;
import com.example.apka_prozdrowotna.service.meal_perdion.SnacksService;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class DailyDietHome implements IDailyDietHome {

    private final MealIngredientRepository mealIngredientRepository;
    private final BreakfastService breakfastService;
    private final LunchService lunchService;
    private final DinnerService dinnerService;
    private final SnacksService snacksService;


    @Override
    public List<MealIngredientDTO> returnIngredientsFromMealPeriod(String mealPeriod) {
        List<MealIngredientDTO> result = new ArrayList<>();
        switch (mealPeriod) {
            case "breakfast":
                result = mealIngredientRepository.findAllByBreakfastIdsWithDuplicates();
            break;
            case "lunch":
                result = mealIngredientRepository.findAllByLunchIdsWithDuplicates();
            break;
            case "dinner":
                result = mealIngredientRepository.findAllByDinnerIdsWithDuplicates();
            break;
            case "snacks":
                result = mealIngredientRepository.findAllBySnacksIdsWithDuplicates();
            break;
            default:
                break;
        }
        return result;
    }

    @Override
    public void deleteIngredientFromMealPeriod(String mealPeriod, Integer ingredientFromMealPeriodId){
        switch (mealPeriod) {
            case "breakfast":
                breakfastService.deleteMealIngredientFromBreakfast(ingredientFromMealPeriodId);
                break;
            case "lunch":
                lunchService.deleteMealIngredientFromLunch(ingredientFromMealPeriodId);
                break;
            case "dinner":
                dinnerService.deleteMealIngredientFromDinner(ingredientFromMealPeriodId);
                break;
            case "snacks":
                snacksService.deleteMealIngredientFromSnacks(ingredientFromMealPeriodId);
                break;
            default:
                break;
        }
    }

}
