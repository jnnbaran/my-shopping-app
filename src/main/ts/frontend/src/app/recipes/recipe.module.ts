import {RouterModule, Routes} from "@angular/router";
import {AuthGuardService} from "../auth/providers/auth-guard.service";
import {RecipesComponent} from "./recipes.component";
import {RecipeEditComponent} from "./recipe-edit/recipe-edit.component";
import {RecipeDetailComponent} from "./recipe-detail/recipe-detail.component";
import {RecipeListComponent} from "./recipe-list/recipe-list.component";

const routes: Routes = [
    {path: 'recipe/', component: RecipesComponent, canActivate: [AuthGuardService]},
];

@NgModule({
    declarations: [RecipesComponent, RecipeEditComponent, RecipeDetailComponent, RecipeListComponent],
    exports: [],
    imports: [
        RouterModule.forChild(routes),

    ],
})
export class RecipeModule {
}