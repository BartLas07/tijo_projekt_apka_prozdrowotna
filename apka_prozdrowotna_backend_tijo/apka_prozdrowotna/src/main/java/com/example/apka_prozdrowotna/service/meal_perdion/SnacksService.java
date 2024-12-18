package com.example.apka_prozdrowotna.service.meal_perdion;

import com.example.apka_prozdrowotna.model.meal_period.Snacks;
import com.example.apka_prozdrowotna.repository.meal_period.SnacksRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SnacksService {

    private final SnacksRepository snacksRepository;

    @Transactional
    public Snacks saveMealIngredientForSnack(Integer mealIngredientId, Integer mealIngredientQuantityInGrams) {
        Snacks snack = new Snacks(mealIngredientId, mealIngredientQuantityInGrams);
        return snacksRepository.save(snack);
    }
    @Transactional
    public void deleteMealIngredientFromSnacks(Integer mealIngredientFromSnacksId) {
        snacksRepository.deleteALlByMealIngredientForSnacksId(mealIngredientFromSnacksId);
    }

}
