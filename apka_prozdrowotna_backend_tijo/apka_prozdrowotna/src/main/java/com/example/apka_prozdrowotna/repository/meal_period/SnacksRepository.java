package com.example.apka_prozdrowotna.repository.meal_period;

import com.example.apka_prozdrowotna.model.meal_period.Snacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SnacksRepository extends JpaRepository<Snacks, Integer> {



    @Modifying
    @Query("DELETE FROM Snacks s WHERE s.mealIngredientForSnacksId = :mealIngredientForMealPeriodId")
    void deleteALlByMealIngredientForSnacksId(@Param("mealIngredientForMealPeriodId") Integer mealIngredientForMealPeriodId);

}
