package com.example.apka_prozdrowotna.service.meal_perdion;

import com.example.apka_prozdrowotna.model.meal_period.Lunch;
import com.example.apka_prozdrowotna.repository.meal_period.LunchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LunchService {

    private final LunchRepository lunchRepository;

    @Transactional
    public Lunch saveMealIngredientForLunch(Integer mealIngredientId, Integer mealIngredientQuantityInGrams) {
        Lunch lunch = new Lunch(mealIngredientId, mealIngredientQuantityInGrams);
        return lunchRepository.save(lunch);
    }

    @Transactional
    public void deleteMealIngredientFromLunch(Integer mealIngredientFromLunchId) {
       lunchRepository.deleteALlByMealIngredientForLunchId(mealIngredientFromLunchId);
    }

}
