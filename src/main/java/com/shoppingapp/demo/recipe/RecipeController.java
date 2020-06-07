package com.shoppingapp.demo.recipe;

import com.shoppingapp.demo.shared.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/recipe")
public class RecipeController {

    private RecipeService recipeService;

    @PostMapping("/create")
    public ResponseEntity<Void> createRecipe(@RequestBody RecipeDTO recipeDTO){
        recipeService.createRecipe(recipeDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/test")
    public ResponseEntity<String> createRecipe(){
        return ResponseEntity.ok("{\"test\": \"udało się\"}");
    }

}
