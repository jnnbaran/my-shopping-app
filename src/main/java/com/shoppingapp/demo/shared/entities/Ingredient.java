package com.shoppingapp.demo.shared.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class Ingredient {

    @NotBlank
    @Size(max = 100)
    private String ingredientName;

    private int amount;
}


