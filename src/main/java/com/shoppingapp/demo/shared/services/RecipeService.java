package com.shoppingapp.demo.shared.services;
import com.shoppingapp.demo.shared.services.UserService;
import com.shoppingapp.demo.recipe.RecipeDTO;
import com.shoppingapp.demo.shared.entities.Recipe;
import com.shoppingapp.demo.shared.entities.User;
import com.shoppingapp.demo.shared.repos.RecipeRepository;
import org.modelmapper.ModelMapper;



import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RecipeService {

    private final ModelMapper modelMapper;
    private final RecipeRepository recipeRepository;
    private  final UserService userService;


    public RecipeService(ModelMapper modelMapper, RecipeRepository recipeRepository,  UserService userService) {
        this.modelMapper = modelMapper;
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }

    public void createRecipe(RecipeDTO recipeDTO){
      // User user = userService.getCurrentAuthenticatedUser();
        Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);
       // recipe.setOwner(user);
        recipeRepository.save(recipe);
    }
}
