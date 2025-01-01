package com.example.apka_prozdrowotna.model.meal_period;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "snacks")
@Getter
@Setter
public class Snacks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mealIngredientForSnacksId;
    private Integer mealIngredientId;
    private Integer mealIngredientQuantityInGrams;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    public Snacks(Integer mealIngredientId, Integer mealIngredientQuantityInGrams) {
        this.mealIngredientId = mealIngredientId;
        this.mealIngredientQuantityInGrams = mealIngredientQuantityInGrams;
    }

    public Snacks() {}
}
