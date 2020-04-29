package com.shoppingapp.demo.shared.model;


import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Ingredient {

    @NotNull
    @Size(max = 100)
    private String ingredientName;

    private int amount;

    public Ingredient (){

    }

    public Ingredient(@NotNull @Size(max = 100) String ingredientName, int amount) {
        this.ingredientName = ingredientName;
        this.amount = amount;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}


