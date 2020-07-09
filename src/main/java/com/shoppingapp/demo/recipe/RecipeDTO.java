package com.shoppingapp.demo.recipe;

import com.shoppingapp.demo.shared.model.Ingredient;
import com.shoppingapp.demo.user.UserDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class RecipeDTO {


    private UserDTO owner;

    @NotNull
    @NotEmpty
    private String name;
    private String description;
    private List<Ingredient> ingredientsList;


    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
}
