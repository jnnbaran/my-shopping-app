import {Recipe} from "./recipe.model";
import {Injectable} from "@angular/core";
import {Ingredient} from "../header/shared/ingredient.model";
import {ShoppingListService} from "../shopping-list/shopping-list.service";
import {Subject} from "rxjs";

@Injectable({providedIn: 'root'})
export class RecipeService {
    recipesChanged = new Subject<Recipe[]>();
     constructor(private slService: ShoppingListService) {}


    private recipes: Recipe [] = [
        new Recipe('A test recipe',
            'This is a simple test',
            'https://img.taste.com.au/Pas8_G-H/w643-h428-cfill-q90/taste/2019/04/healthy-chilli-con-carne-148825-1.jpg',
            [new Ingredient('Meat', 1),
                       new Ingredient('French Fries', 2)]),
        new Recipe('Another test recipe',
            'something will be here',
            'https://img.taste.com.au/Pas8_G-H/w643-h428-cfill-q90/taste/2019/04/healthy-chilli-con-carne-148825-1.jpg',
            [new Ingredient('Buns', 1),
                     new Ingredient('Eggs', 3)])

    ];

    setRecipes(recipes: Recipe[]){
        this.recipes = recipes;
        this.recipesChanged.next(this.recipes.slice());
    }

    addIngredientsToShoppingList(ingredients: Ingredient[]){
        this.slService.addIngredients(ingredients);
    }

    getRecipe(id: number){
        return this.recipes.slice()[id];
    }
   getRecipes(){
       return this.recipes.slice();
   }

   addRecipe(recipe: Recipe){
        this.recipes.push(recipe);
        this.recipesChanged.next(this.recipes.slice());
   }

   updateRecipe(index: number, newRecipe: Recipe){
        this.recipes[index] = newRecipe;
        this.recipesChanged.next(this.recipes.slice());
   }

   deleteRecipe(index: number){
        this.recipes.splice(index, 1);
        this.recipesChanged.next(this.recipes.slice());

   }
}

