package com.example.apka_prozdrowotna.repository.meal_period;

import com.example.apka_prozdrowotna.model.dto.MealIngredientDTO;
import com.example.apka_prozdrowotna.model.meal_period.Dinner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DinnerRepository extends JpaRepository<Dinner, Integer> {

    @Modifying
    @Query("DELETE FROM Dinner d WHERE d.mealIngredientForDinnerId = :mealIngredientForMealPeriodId")
    void deleteALlByMealIngredientForDinnerId(@Param("mealIngredientForMealPeriodId") Integer mealIngredientForMealPeriodId);



}
