package com.example.apka_prozdrowotna.model.json;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealIngredientSearchedOptionResponseJSON {


    private String value;
    private String label;
    private Integer mealIngredientQuantityInGrams;
    private Integer mealIngradientId;



    public MealIngredientSearchedOptionResponseJSON(String value, String label, Integer mealIngredientQuantityInGrams, Integer mealIngradientId) {
        this.value = value;
        this.label = label;
        this.mealIngredientQuantityInGrams = mealIngredientQuantityInGrams;
        this.mealIngradientId = mealIngradientId;

    }

    public MealIngredientSearchedOptionResponseJSON() {}

}
