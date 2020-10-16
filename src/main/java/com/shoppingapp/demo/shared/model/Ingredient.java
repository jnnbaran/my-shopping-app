package com.shoppingapp.demo.shared.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class Ingredient {

    @NotNull
    @Size(max = 100)
    private String ingredientName;

    private int amount;
}


