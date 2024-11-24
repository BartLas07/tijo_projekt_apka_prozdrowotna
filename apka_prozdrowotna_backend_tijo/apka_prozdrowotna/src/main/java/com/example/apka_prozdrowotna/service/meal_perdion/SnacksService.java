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
    public Snacks saveSnack(Integer meal_ingredient_id, Integer quantity_of_grams) {
        Snacks snack = new Snacks(meal_ingredient_id, quantity_of_grams);
        return snacksRepository.save(snack);
    }
    @Transactional
    public void deleteSnacks(Integer typeOfMealIngredientId) {
        snacksRepository.deleteALlBySnacks_id(typeOfMealIngredientId);
    }

}
