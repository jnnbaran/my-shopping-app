package com.shoppingapp.demo.recipe;

import com.shoppingapp.demo.shared.entities.Ingredient;
import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO {

    private Long id;
    private String name;
    private String description;
    private List<Ingredient> ingredientsList;
}
