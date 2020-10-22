package com.shoppingapp.demo.recipe;

import com.shoppingapp.demo.profile.ProfileDTO;
import com.shoppingapp.demo.shared.model.Ingredient;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RecipeDTO {

    private ProfileDTO owner;

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    private List<Ingredient> ingredientsList;
}
