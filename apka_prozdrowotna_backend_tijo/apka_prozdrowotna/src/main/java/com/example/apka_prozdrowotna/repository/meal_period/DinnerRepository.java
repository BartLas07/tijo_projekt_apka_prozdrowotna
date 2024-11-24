package com.example.apka_prozdrowotna.repository.meal_period;

import com.example.apka_prozdrowotna.model.dto.MealIngredientDTO;
import com.example.apka_prozdrowotna.model.meal_period.Dinner;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apka_prozdrowotna.model.Meal_Ingredient;
import com.example.apka_prozdrowotna.model.dto.MealIngredientDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DinnerRepository extends JpaRepository<Dinner, Integer> {

    @Modifying
    @Query("DELETE FROM Dinner d WHERE d.dinner_id = :typeOfMealIngredientId")
    void deleteALlByDinner_id(@Param("typeOfMealIngredientId") Integer typeOfMealIngredientId);

    @Query("SELECT new com.example.apka_prozdrowotna.model.dto.MealIngredientDTO(" +
            "CAST(ROUND(SUM(mi.protein * (d.meal_quantity_of_grams / 100.0)), 3) AS double), " +
            "CAST(ROUND(SUM(mi.carbohydrates * (d.meal_quantity_of_grams / 100.0)), 3) AS double), " +
            "CAST(ROUND(SUM(mi.sodium * (d.meal_quantity_of_grams / 100.0)), 3) AS double), " +
            "CAST(ROUND(SUM(mi.calories * (d.meal_quantity_of_grams / 100.0)), 3) AS double), " +
            "CAST(ROUND(SUM(mi.fats * (d.meal_quantity_of_grams / 100.0)), 3) AS double), " +
            "CAST(ROUND(SUM(mi.cholesterol * (d.meal_quantity_of_grams / 100.0)), 3) AS double), " +
            "CAST(ROUND(SUM(mi.sugar * (d.meal_quantity_of_grams / 100.0)), 3) AS double), " +
            "CAST(ROUND(SUM(mi.fiber * (d.meal_quantity_of_grams / 100.0)), 3) AS double)" +
            ") " +
            "FROM Meal_Ingredient mi JOIN Dinner d ON mi.meal_ingredient_id = d.meal_ingredient_id")
    MealIngredientDTO sumAllDinnerIngredients();


}
