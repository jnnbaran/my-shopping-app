package com.shoppingapp.demo.recipe;

import com.shoppingapp.demo.profile.UserService;
import com.shoppingapp.demo.recipe.dto.RecipeDTO;
import com.shoppingapp.demo.shared.entities.Recipe;
import com.shoppingapp.demo.shared.entities.User;
import com.shoppingapp.demo.shared.repos.RecipeRepository;
import org.modelmapper.ModelMapper;


import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeService {

    private final ModelMapper modelMapper;
    private final RecipeRepository recipeRepository;
    private final UserService userService;

    public RecipeService(ModelMapper modelMapper, RecipeRepository recipeRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }

    public List<RecipeDTO> getUserRecipes(long id) {
        return recipeRepository.findAllByOwner_Id(id)
                .stream()
                .map(recipe -> modelMapper.map(recipe, RecipeDTO.class))
                .collect(Collectors.toList());
    }

    public void saveRecipes(List<RecipeDTO> recipeDTO) {
        User user = userService.getCurrentUser();
        List<Recipe> recipes = recipeDTO.stream()
                .map(dto -> modelMapper.map(dto, Recipe.class))
                .collect(Collectors.toList());
        recipes.forEach(recipe -> recipe.setOwner(user));
        recipeRepository.deleteAllByOwner_Id(user.getId());
        recipeRepository.saveAll(recipes);
    }
}
