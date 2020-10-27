import {Component, OnInit} from '@angular/core';
import {Recipe} from '../recipe.model';
import {RecipeService} from '../recipe.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {PopoverController} from '@ionic/angular';
import {RecipePopoverComponent} from '../recipe-popover/recipe-popover.component';

@Component({
    selector: 'app-recipe-detail',
    templateUrl: './recipe-detail.component.html',
    styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {
    recipe: Recipe;
    id: number;

    constructor(
        private recipeService: RecipeService,
        private route: ActivatedRoute,
        private router: Router,
        private popoverController: PopoverController
    ) {
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => console.log(params.id));
        this.route.params
            .subscribe(
                (params: Params) => {
                    this.id = +params.id;
                    this.recipe = this.recipeService.getRecipe(this.id);
                }
            );
    }

    async showPopover(ev: Event) {
        const popover = await this.popoverController.create({
            component: RecipePopoverComponent,
            cssClass: 'my-custom-class',
            event: ev,
            translucent: true,
            componentProps: { recipeIndex: this.id },
        });
        return await popover.present();
    }
}
