package com.example.apka_prozdrowotna.repository.meal_period;


import com.example.apka_prozdrowotna.model.meal_period.Breakfast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BreakfastRepository extends JpaRepository<Breakfast, Integer> {
    @Modifying
    @Query("DELETE FROM Breakfast b WHERE b.meal_ingredient_id = :mealIngredientId")
    void deleteAllByMealIngredientId(@Param("mealIngredientId") Integer mealIngredientId);

    @Modifying
    @Query("DELETE FROM Breakfast b WHERE b.breakfast_id = :typeOfMealIngredientId")
    void deleteALlByBreakfast_id(@Param("typeOfMealIngredientId") Integer typeOfMealIngredientId);


}
