import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RecipeService} from '../../recipes/recipe.service';
import {Recipe} from '../../recipes/recipe.model';
import {environment} from '../../../environments/environment';

@Injectable({providedIn: 'root'})
export class DataStorageService {
    constructor(private  http: HttpClient, private recipeService: RecipeService) {
    }

    stroreRecipes() {
        const recipes = this.recipeService.getRecipes();
        return  this.http
            .post(
                environment.api + 'recipes/save',
                recipes
            )
            .subscribe(console.log);
    }

    fetchRecipes() {
        this.http
            .get<Recipe[]>(environment.api + 'recipes/')
            .subscribe(recipes => {
                console.log(recipes);
                this.recipeService.setRecipes(recipes);
            } );
    }
}
