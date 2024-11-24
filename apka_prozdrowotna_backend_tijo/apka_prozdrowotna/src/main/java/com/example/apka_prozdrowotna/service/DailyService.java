package com.example.apka_prozdrowotna.service;

import com.example.apka_prozdrowotna.model.Meal_Ingredient;
import com.example.apka_prozdrowotna.repository.Meal_IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@Service
@RequiredArgsConstructor
public class DailyService {

    private final Meal_IngredientRepository meal_IngredientRepository;

    @GetMapping ("/a")
    public List<Meal_Ingredient> getAll(){
        return meal_IngredientRepository.findAll();
    }

/*
    public List<dateDietDay> getDayDietDay(Integer id){
        return dietDayRepository.findBydateDietDayid(id);
    }
    */

}
