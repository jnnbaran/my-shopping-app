package com.shoppingapp.demo.recipe;

import com.shoppingapp.demo.profile.UserService;
import com.shoppingapp.demo.recipe.dto.RecipeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipes")
public class RecipeController {

    private UserService userService;
    private RecipeService recipeService;

    public RecipeController(UserService userService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RecipeDTO>> getRecipes() {
        return ResponseEntity.ok(
                recipeService.getUserRecipes(
                        userService.getCurrentUser().getId()));
    }

    @PostMapping("/save")
    public ResponseEntity<Void> createRecipe(@RequestBody List<RecipeDTO> recipeDTO) {
        recipeService.saveRecipes(recipeDTO);
        return ResponseEntity.ok().build();
    }
}
