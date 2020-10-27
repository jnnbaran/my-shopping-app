import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RecipeService} from '../recipe.service';

@Component({
    selector: 'app-recipe-popover',
    templateUrl: './recipe-popover.component.html',
    styleUrls: ['./recipe-popover.component.css']
})
export class RecipePopoverComponent implements OnInit {

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private recipeService: RecipeService,
    ) {
    }

    @Input()
    recipeIndex: number;

    ngOnInit(): void {
    }

    onAddToShoppingList() {
        this.recipeService.addIngredientsToShoppingList(this.recipeIndex);
    }

    onEditRecipe() {
        this.router.navigate(['recipes', this.recipeIndex, 'edit']);
    }

    onDeleteRecipe() {
        this.recipeService.deleteRecipe(this.recipeIndex);
        this.router.navigate(['/recipes']);
    }

}
