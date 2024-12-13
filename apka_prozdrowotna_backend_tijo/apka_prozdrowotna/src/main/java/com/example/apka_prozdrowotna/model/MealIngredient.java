package com.example.apka_prozdrowotna.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "meal_ingredient")
@Getter
@Setter


public class MealIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer meal_ingredient_id ;
    private String ingredient;
    private Double protein;
    private Double carbohydrates;
    private Double sodium;
    private Double calories;
    private Double fats;
    private Double cholesterol;
    private Double sugar;
    private Double fiber;



    public MealIngredient(String ingredient, Double protein, Double carbohydrates, Double sodium, Double calories, Double fats, Double cholesterol, Double sugar, Double fiber)
    {
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

    public MealIngredient(String ingredient)
    {
        this.ingredient = ingredient;


    }

    public MealIngredient() {

    }
}
