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
    public Dinner saveDinner(Integer meal_ingredient_id, Integer quantity_of_grams) {
        Dinner dinner = new Dinner(meal_ingredient_id, quantity_of_grams);
        return dinnerRepository.save(dinner);
    }

    @Transactional
    public void deleteDinner(Integer typeOfMealIngredientId) {
        dinnerRepository.deleteALlByDinner_id(typeOfMealIngredientId);
    }

    public MealIngredientDTO getSumAllDinnerIngredients() {
        return dinnerRepository.sumAllDinnerIngredients();
    }
}
