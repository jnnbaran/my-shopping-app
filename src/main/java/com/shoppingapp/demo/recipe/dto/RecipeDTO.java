package com.shoppingapp.demo.recipe.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO {

    private Long id;
    private String name;
    private String description;
    private String imagePath;
    private List<IngredientDTO> ingredientsList;
}
