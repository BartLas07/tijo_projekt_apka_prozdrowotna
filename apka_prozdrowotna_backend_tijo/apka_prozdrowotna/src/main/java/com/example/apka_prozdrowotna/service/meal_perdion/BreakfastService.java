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
    public Breakfast saveBreakfast(Integer meal_ingradient_id, Integer meal_quantity_of_grams) {
        Breakfast breakfast = new Breakfast(meal_ingradient_id, meal_quantity_of_grams);
        return breakfastRepository.save(breakfast);
    }

    @Transactional
    public void deleteBreakfast(Integer typeOfMealIngredientId) {
        breakfastRepository.deleteALlByBreakfast_id(typeOfMealIngredientId);
    }
}
