import {Ingredient} from "../header/shared/ingredient.model";

export class Recipe {
    public name: string;
    public imagePath: string;
    public description: string;
    public ingredientsList: Ingredient[];

    constructor(name:string, description:string, imagePath: string, ingredients: Ingredient[]){
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.ingredientsList = ingredients;
    }
}