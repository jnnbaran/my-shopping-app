import {Recipe} from "./recipe.model";
import {EventEmitter, Injectable} from "@angular/core";
import {Ingredient} from "../header/shared/ingredient.model";
import {ShoppingListService} from "../shopping-list/shopping-list.service";

@Injectable()
export class RecipeService {

    recipeSelected = new EventEmitter<Recipe>();
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

    addIngredientsToShoppingList(ingredients: Ingredient[]){
        this.slService.addIngredients(ingredients);
    }

   getRecipes(){
       return this.recipes.slice();
   }
}

