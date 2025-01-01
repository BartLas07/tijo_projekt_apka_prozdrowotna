package com.example.apka_prozdrowotna.repository.meal_period;

import com.example.apka_prozdrowotna.model.meal_period.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LunchRepository extends JpaRepository<Lunch, Integer> {


    @Modifying
    @Query("DELETE FROM Lunch l WHERE l.mealIngredientForLunchId = :mealIngredientForMealPeriodId")
    void deleteALlByMealIngredientForLunchId(@Param("mealIngredientForMealPeriodId") Integer mealIngredientForMealPeriodId);
}
