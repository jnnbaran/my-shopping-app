import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {RecipeService} from "../../recipes/recipe.service";
import {Recipe} from "../../recipes/recipe.model";

@Injectable({providedIn: 'root'})
export class DataStorageService{
    constructor(private  http: HttpClient, private recipeService: RecipeService) {
    }

    stroreRecipes(){
        const recipes = this.recipeService.getRecipes();
        return  this.http
            .post(
                'http://localhost:8080/api/recipes/save',
                recipes
            )
            .subscribe(console.log);
    }

    fetchRecipes() {
        this.http
            .get<Recipe[]>('http://localhost:8080/api/recipes/')
            .subscribe(recipes => {
                console.log(recipes);
                this.recipeService.setRecipes(recipes);
            } )
    }
}