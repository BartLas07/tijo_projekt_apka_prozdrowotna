package com.example.apka_prozdrowotna.service.meal_perdion;

import com.example.apka_prozdrowotna.model.meal_period.Breakfast;
import com.example.apka_prozdrowotna.repository.meal_period.BreakfastRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BreakfastService {

    private final BreakfastRepository breakfastRepository;



    @Transactional
    public Breakfast saveMealInfredientsForBreakfast(Integer mealIngredientId, Integer mealIngredientQuantityInGrams) {
        Breakfast breakfast = new Breakfast(mealIngredientId, mealIngredientQuantityInGrams);
        return breakfastRepository.save(breakfast);
    }

    @Transactional
    public void deleteMealIngredientFromBreakfast(Integer mealIngradientFromBreakfastId) {
        breakfastRepository.deleteALlByMealIngredientForBreakfastId(mealIngradientFromBreakfastId);
    }
}
