package com.example.apka_prozdrowotna.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity

public class DateDietDay {
    @Id
    private Integer dateDietDayid;
    private Integer year;
    private Integer month;
    private Integer day;
}
