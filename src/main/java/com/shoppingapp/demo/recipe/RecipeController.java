package com.shoppingapp.demo.recipe;

import com.shoppingapp.demo.profile.UserService;
import com.shoppingapp.demo.shared.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipe")
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

    @PostMapping("/add")
    public ResponseEntity<Void> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        recipeService.createRecipe(recipeDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/test")
    public ResponseEntity<String> createRecipe() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(String.format("{\"test\": \"udało się %s\" }", user.getEmail()));
    }
}
