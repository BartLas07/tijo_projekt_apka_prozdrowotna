package com.example.apka_prozdrowotna.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsDTO {
    private String gender;
    private String activityLevel;
    private String weightPreference;
    private Double weight;
    private Double height;
    private Integer age;

    public UserDetailsDTO() {}
}
