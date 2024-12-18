package com.example.apka_prozdrowotna.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class MealIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mealIngredientId ;
    private String mealIngredientName;
    private Double protein;
    private Double carbohydrates;
    private Double sodium;
    private Double calories;
    private Double fats;
    private Double cholesterol;
    private Double sugar;
    private Double fiber;



    public MealIngredient(String mealIngredientName, Double protein, Double carbohydrates, Double sodium, Double calories, Double fats, Double cholesterol, Double sugar, Double fiber)
    {
        this.mealIngredientName = mealIngredientName;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.sodium = sodium;
        this.calories = calories;
        this.fats = fats;
        this.cholesterol = cholesterol;
        this.sugar = sugar;
        this.fiber = fiber;

    }

    public MealIngredient(String mealIngredientName)
    {
       this.mealIngredientName = mealIngredientName;
    }

    public MealIngredient() {

    }
}
