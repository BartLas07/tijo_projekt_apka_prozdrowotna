package com.example.apka_prozdrowotna.model.meal_period;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Breakfast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mealIngredientForBreakfastId;
    private Integer mealIngredientId;
    private Integer mealIngredientQuantityInGrams;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    public Breakfast(Integer mealIngredientId, Integer mealIngredientQuantityInGrams) {
        this.mealIngredientId = mealIngredientId;
        this.mealIngredientQuantityInGrams = mealIngredientQuantityInGrams;
    }

    public Breakfast() {}
}
