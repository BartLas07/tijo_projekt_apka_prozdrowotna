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
    public Lunch saveLunch(Integer meal_ingredient_id, Integer meal_quantity_of_grams) {
        Lunch lunch = new Lunch(meal_ingredient_id, meal_quantity_of_grams);
        return lunchRepository.save(lunch);
    }

    @Transactional
    public void deleteLunch(Integer typeOfMealIngredientId) {
       lunchRepository.deleteALlByLunch_id(typeOfMealIngredientId);
    }

}
