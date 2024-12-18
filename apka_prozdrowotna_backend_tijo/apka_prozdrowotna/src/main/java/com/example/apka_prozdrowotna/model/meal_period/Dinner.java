package com.example.apka_prozdrowotna.model.meal_period;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Dinner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mealIngredientForDinnerId;
    private Integer mealIngredientId;
    private Integer mealIngredientQuantityInGrams;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    public Dinner(Integer mealIngredientId, Integer mealIngredientQuantityInGram) {
        this.mealIngredientId = mealIngredientId;
        this.mealIngredientQuantityInGrams = mealIngredientQuantityInGram;
    }

    public Dinner() {}
}
