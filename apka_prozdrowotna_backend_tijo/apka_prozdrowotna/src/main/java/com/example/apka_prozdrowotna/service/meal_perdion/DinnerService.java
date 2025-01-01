package com.example.apka_prozdrowotna.service.meal_perdion;

import com.example.apka_prozdrowotna.model.dto.MealIngredientDTO;
import com.example.apka_prozdrowotna.model.meal_period.Dinner;
import com.example.apka_prozdrowotna.repository.meal_period.DinnerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DinnerService {

    private final DinnerRepository dinnerRepository;

    @Transactional
    public Dinner saveMealIngredientForDinner(Integer mealIngredientId, Integer mealIngredientQuantityInGrams) {
        Dinner dinner = new Dinner(mealIngredientId, mealIngredientQuantityInGrams);
        return dinnerRepository.save(dinner);
    }

    @Transactional
    public void deleteMealIngredientFromDinner(Integer mealIngredientFromDinnerId) {
        dinnerRepository.deleteALlByMealIngredientForDinnerId(mealIngredientFromDinnerId);
    }

}
