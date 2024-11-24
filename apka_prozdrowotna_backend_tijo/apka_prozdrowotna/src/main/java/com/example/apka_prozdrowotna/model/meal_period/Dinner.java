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
    private Integer dinner_id;
    private Integer meal_ingredient_id;
    private Integer meal_quantity_of_grams;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    public Dinner(Integer meal_ingredient_id, Integer quantity_of_grams) {
        this.meal_ingredient_id = meal_ingredient_id;
        this.meal_quantity_of_grams = quantity_of_grams;
    }

    public Dinner() {}
}
