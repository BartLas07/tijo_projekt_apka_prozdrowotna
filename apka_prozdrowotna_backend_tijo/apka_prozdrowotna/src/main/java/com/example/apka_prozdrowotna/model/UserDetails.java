package com.example.apka_prozdrowotna.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userDetailsId ;
    private Integer caloriePool;
    private Double recommendedHydration;
    private Double bmi;

    public UserDetails(Integer caloriePool, Double recommendedHydration, Double bmi) {
        this.caloriePool = caloriePool;
        this.recommendedHydration = recommendedHydration;
        this.bmi = bmi;
    }

    public UserDetails() {}
}
