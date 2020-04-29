package com.shoppingapp.demo.profile;

import com.shoppingapp.demo.recipe.RecipeDTO;
import com.shoppingapp.demo.shared.entities.Recipe;

import java.util.List;

public class ProfileDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private List<RecipeDTO> ownedRecipes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public List<RecipeDTO> getOwnedRecipes() {
        return ownedRecipes;
    }

    public void setOwnedRecipes(List<RecipeDTO> ownedRecipes) {
        this.ownedRecipes = ownedRecipes;
    }
}
