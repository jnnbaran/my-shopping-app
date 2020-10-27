import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RecipesComponent} from './recipes/recipes.component';
import {ShoppingListComponent} from './shopping-list/shopping-list.component';
import {RecipeStartComponent} from './recipes/recipe-start/recipe-start.component';
import {RecipeDetailComponent} from './recipes/recipe-detail/recipe-detail.component';
import {RecipeEditComponent} from './recipes/recipe-edit/recipe-edit.component';
import {WelcomeScreenComponent} from './welcome-screen/welcome-screen.component';
import {AuthGuardService} from './auth/providers/auth-guard.service';


const approutes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: WelcomeScreenComponent},
    {path: 'recipes', component: RecipesComponent, canActivate: [AuthGuardService], children: [
            { path: '', component: RecipeStartComponent },
            { path: 'new', component: RecipeEditComponent},
            { path: ':id', component: RecipeDetailComponent},
            { path: ':id/edit', component: RecipeEditComponent},
        ]},
    {path: 'shopping-list', component: ShoppingListComponent, canActivate: [AuthGuardService]},
];

@NgModule({
  imports: [RouterModule.forRoot(approutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
