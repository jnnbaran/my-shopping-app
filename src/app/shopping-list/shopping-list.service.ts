import {Ingredient} from "../header/shared/ingredient.model";
import {EventEmitter} from "@angular/core";

export class ShoppingListService {
    ingredientsChanged = new EventEmitter<Ingredient[]>();
    private ingredients: Ingredient[]= [
        new Ingredient('Apples', 5),
        new Ingredient('Tomatoes', 3),
    ];

    getIngredients(){
        return this.ingredients.slice();
    }

    addIngrediet(ingredient: Ingredient){
        this.ingredients.push(ingredient)
        this.ingredientsChanged.emit(this.ingredients.slice());
    }

    addIngredients(ingredients: Ingredient[]){
      //  for(let ingredient of ingredients){
        //    this.addIngrediet(ingredient);
        //}
        this.ingredients.push(...ingredients);
        this.ingredientsChanged.emit(this.ingredients.slice());
    }
}