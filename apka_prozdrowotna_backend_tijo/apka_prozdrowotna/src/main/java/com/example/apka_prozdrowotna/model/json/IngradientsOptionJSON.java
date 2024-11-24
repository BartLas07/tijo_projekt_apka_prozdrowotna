package com.example.apka_prozdrowotna.model.json;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngradientsOptionJSON {

private String value;
private String label;



public IngradientsOptionJSON(String value, String label) {
    this.value = value;
    this.label = label;

}

public IngradientsOptionJSON() {}
}
