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
    private Integer breakfast_id;
    private Integer meal_ingredient_id;
    private Integer meal_quantity_of_grams;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    public Breakfast(Integer meal_ingredient_id, Integer meal_quantity_of_grams) {
        this.meal_ingredient_id = meal_ingredient_id;
        this.meal_quantity_of_grams = meal_quantity_of_grams;
    }

    public Breakfast() {}
}
