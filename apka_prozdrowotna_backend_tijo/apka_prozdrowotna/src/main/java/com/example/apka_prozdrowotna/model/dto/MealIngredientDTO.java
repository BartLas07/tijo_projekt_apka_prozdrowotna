package com.example.apka_prozdrowotna.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealIngredientDTO {
    private Integer mealIngredientId;
    private Integer mealPeriodIngredientId;
    private String ingredient;
    private Double protein;
    private Double carbohydrates;
    private Double sodium;
    private Double calories;
    private Double fats;
    private Double cholesterol;
    private Double sugar;
    private Double fiber;

    public MealIngredientDTO(Integer mealIngredientId, Integer mealPeriodIngredientId, String ingredient, Double protein, Double carbohydrates, Double sodium, Double calories, Double fats, Double cholesterol, Double sugar, Double fiber) {
        this.mealPeriodIngredientId = mealPeriodIngredientId;
        this.mealIngredientId = mealIngredientId;
        this.ingredient = ingredient;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.sodium = sodium;
        this.calories = calories;
        this.fats = fats;
        this.cholesterol = cholesterol;
        this.sugar = sugar;
        this.fiber = fiber;
    }
    public MealIngredientDTO( Double protein, Double carbohydrates, Double sodium, Double calories, Double fats, Double cholesterol, Double sugar, Double fiber) {

        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.sodium = sodium;
        this.calories = calories;
        this.fats = fats;
        this.cholesterol = cholesterol;
        this.sugar = sugar;
        this.fiber = fiber;
    }

}
