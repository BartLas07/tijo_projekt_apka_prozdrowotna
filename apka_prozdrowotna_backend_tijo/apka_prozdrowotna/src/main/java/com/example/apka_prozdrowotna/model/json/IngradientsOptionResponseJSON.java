package com.example.apka_prozdrowotna.model.json;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngradientsOptionResponseJSON {


    private String value;
    private String label;
    private Integer quantity_of_grams;
    private Integer meal_ingradient_id;



    public IngradientsOptionResponseJSON(String value, String label, Integer quantity_of_grams, Integer meal_ingradient_id) {
        this.value = value;
        this.label = label;
        this.quantity_of_grams = quantity_of_grams;
        this.meal_ingradient_id = meal_ingradient_id;

    }

    public IngradientsOptionResponseJSON() {}

}
