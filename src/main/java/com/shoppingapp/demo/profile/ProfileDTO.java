package com.shoppingapp.demo.profile;

import com.shoppingapp.demo.recipe.RecipeDTO;
import com.shoppingapp.demo.shared.entities.Recipe;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private List<RecipeDTO> ownedRecipes;

}
