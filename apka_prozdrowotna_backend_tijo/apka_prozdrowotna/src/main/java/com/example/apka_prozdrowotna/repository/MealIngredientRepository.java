package com.example.apka_prozdrowotna.repository;

import com.example.apka_prozdrowotna.model.MealIngredient;
import com.example.apka_prozdrowotna.model.dto.MealIngredientDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealIngredientRepository extends JpaRepository<MealIngredient, Integer> {

   List<MealIngredient> findBySodium(Integer sodium);
   MealIngredient findByIngredient(String ingredient);
   List<MealIngredient> findAll();

   // Znajdź wszystkie składniki dla przekąsek z uwzględnieniem duplikatów
   @Query("SELECT new com.example.apka_prozdrowotna.model.dto.MealIngredientDTO(" +
           "mi.meal_ingredient_id, s.snacks_id, mi.ingredient, " +
           "CAST(ROUND(mi.protein * (s.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.carbohydrates * (s.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sodium * (s.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.calories * (s.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fats * (s.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.cholesterol * (s.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sugar * (s.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fiber * (s.meal_quantity_of_grams / 100.0), 3) AS double)) " +
           "FROM MealIngredient mi JOIN Snacks s ON mi.meal_ingredient_id = s.meal_ingredient_id " +
           "ORDER BY s.created_at ASC")
   List<MealIngredientDTO> findAllBySnacksIdsWithDuplicates();

   // Znajdź wszystkie składniki dla śniadania z uwzględnieniem duplikatów
   @Query("SELECT new com.example.apka_prozdrowotna.model.dto.MealIngredientDTO(" +
           "mi.meal_ingredient_id, b.breakfast_id, mi.ingredient, " +
           "CAST(ROUND(mi.protein * (b.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.carbohydrates * (b.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sodium * (b.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.calories * (b.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fats * (b.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.cholesterol * (b.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sugar * (b.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fiber * (b.meal_quantity_of_grams / 100.0), 3) AS double)) " +
           "FROM MealIngredient mi JOIN Breakfast b ON mi.meal_ingredient_id = b.meal_ingredient_id " +
           "ORDER BY b.created_at ASC")
   List<MealIngredientDTO> findAllByBreakfastIdsWithDuplicates();

   // Znajdź wszystkie składniki dla obiadu z uwzględnieniem duplikatów
   @Query("SELECT new com.example.apka_prozdrowotna.model.dto.MealIngredientDTO(" +
           "mi.meal_ingredient_id, l.lunch_id, mi.ingredient, " +
           "CAST(ROUND(mi.protein * (l.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.carbohydrates * (l.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sodium * (l.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.calories * (l.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fats * (l.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.cholesterol * (l.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sugar * (l.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fiber * (l.meal_quantity_of_grams / 100.0), 3) AS double)) " +
           "FROM MealIngredient mi JOIN Lunch l ON mi.meal_ingredient_id = l.meal_ingredient_id " +
           "ORDER BY l.created_at ASC")
   List<MealIngredientDTO> findAllByLunchIdsWithDuplicates();

   // Znajdź wszystkie składniki dla kolacji z uwzględnieniem duplikatów
   @Query("SELECT new com.example.apka_prozdrowotna.model.dto.MealIngredientDTO(" +
           "mi.meal_ingredient_id, d.dinner_id, mi.ingredient, " +
           "CAST(ROUND(mi.protein * (d.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.carbohydrates * (d.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sodium * (d.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.calories * (d.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fats * (d.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.cholesterol * (d.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sugar * (d.meal_quantity_of_grams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fiber * (d.meal_quantity_of_grams / 100.0), 3) AS double)) " +
           "FROM MealIngredient mi JOIN Dinner d ON mi.meal_ingredient_id = d.meal_ingredient_id " +
           "ORDER BY d.created_at ASC")
   List<MealIngredientDTO> findAllByDinnerIdsWithDuplicates();

}
