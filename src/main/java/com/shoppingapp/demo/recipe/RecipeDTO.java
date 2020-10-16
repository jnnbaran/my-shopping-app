package com.shoppingapp.demo.recipe;

import com.shoppingapp.demo.profile.ProfileDTO;
import com.shoppingapp.demo.shared.entities.User;
import lombok.Data;

@Data
public class RecipeDTO {

    private Long id;
    private String name;
    private ProfileDTO owner;

}
