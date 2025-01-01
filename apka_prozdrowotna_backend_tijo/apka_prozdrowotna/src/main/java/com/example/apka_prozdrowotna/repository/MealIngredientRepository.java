package com.example.apka_prozdrowotna.repository;

import com.example.apka_prozdrowotna.model.MealIngredient;
import com.example.apka_prozdrowotna.model.dto.MealIngredientDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealIngredientRepository extends JpaRepository<MealIngredient, Integer> {


   MealIngredient findByMealIngredientName(String ingredient);
   List<MealIngredient> findAll();
   void deleteAll();

   // Znajdź wszystkie składniki dla przekąsek z uwzględnieniem duplikatów
   @Query("SELECT new com.example.apka_prozdrowotna.model.dto.MealIngredientDTO(" +
           "mi.mealIngredientId, s.mealIngredientForSnacksId, mi.mealIngredientName, " +
           "CAST(ROUND(mi.protein * (s.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.carbohydrates * (s.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sodium * (s.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.calories * (s.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fats * (s.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.cholesterol * (s.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sugar * (s.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fiber * (s.mealIngredientQuantityInGrams / 100.0), 3) AS double)) " +
           "FROM MealIngredient  mi JOIN Snacks s ON mi.mealIngredientId = s.mealIngredientId " +
           "ORDER BY s.created_at ASC")
   List<MealIngredientDTO> findAllBySnacksIdsWithDuplicates();

   // Znajdź wszystkie składniki dla śniadania z uwzględnieniem duplikatów
   @Query("SELECT new com.example.apka_prozdrowotna.model.dto.MealIngredientDTO(" +
           "mi.mealIngredientId, b.mealIngredientForBreakfastId, mi.mealIngredientName, " +
           "CAST(ROUND(mi.protein * (b.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.carbohydrates * (b.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sodium * (b.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.calories * (b.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fats * (b.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.cholesterol * (b.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sugar * (b.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fiber * (b.mealIngredientQuantityInGrams / 100.0), 3) AS double)) " +
           "FROM MealIngredient mi JOIN Breakfast b ON mi.mealIngredientId = b.mealIngredientId " +
           "ORDER BY b.created_at ASC")
   List<MealIngredientDTO> findAllByBreakfastIdsWithDuplicates();

   // Znajdź wszystkie składniki dla obiadu z uwzględnieniem duplikatów
   @Query("SELECT new com.example.apka_prozdrowotna.model.dto.MealIngredientDTO(" +
           "mi.mealIngredientId, l.mealIngredientForLunchId, mi.mealIngredientName, " +
           "CAST(ROUND(mi.protein * (l.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.carbohydrates * (l.mealIngredientQuantityInGrams/ 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sodium * (l.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.calories * (l.mealIngredientQuantityInGrams/ 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fats * (l.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.cholesterol * (l.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sugar * (l.mealIngredientQuantityInGrams/ 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fiber * (l.mealIngredientQuantityInGrams / 100.0), 3) AS double)) " +
           "FROM MealIngredient mi JOIN Lunch l ON mi.mealIngredientId= l.mealIngredientId " +
           "ORDER BY l.created_at ASC")
   List<MealIngredientDTO> findAllByLunchIdsWithDuplicates();

   // Znajdź wszystkie składniki dla kolacji z uwzględnieniem duplikatów
   @Query("SELECT new com.example.apka_prozdrowotna.model.dto.MealIngredientDTO(" +
           "mi.mealIngredientId, d.mealIngredientForDinnerId, mi.mealIngredientName, " +
           "CAST(ROUND(mi.protein * (d.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.carbohydrates * (d.mealIngredientQuantityInGrams/ 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sodium * (d.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.calories * (d.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fats * (d.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.cholesterol * (d.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.sugar * (d.mealIngredientQuantityInGrams / 100.0), 3) AS double), " +
           "CAST(ROUND(mi.fiber * (d.mealIngredientQuantityInGrams/ 100.0), 3) AS double)) " +
           "FROM MealIngredient mi JOIN Dinner d ON mi.mealIngredientId = d.mealIngredientId " +
           "ORDER BY d.created_at ASC")
   List<MealIngredientDTO> findAllByDinnerIdsWithDuplicates();

}
