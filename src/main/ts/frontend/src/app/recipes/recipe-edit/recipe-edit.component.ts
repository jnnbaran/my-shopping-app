import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {RecipeService} from "../recipe.service";
import {Recipe} from "../recipe.model";
import {Camera, CameraResultType} from "@capacitor/core";

@Component({
    selector: 'app-recipe-edit',
    templateUrl: './recipe-edit.component.html',
    styleUrls: ['./recipe-edit.component.css']
})
export class RecipeEditComponent implements OnInit {

    id: number;
    editMode = false;
    recipeForm: FormGroup;

    constructor(private route: ActivatedRoute, private recipeService: RecipeService, private router: Router) {
    }

    ngOnInit() {
        this.route.params
            .subscribe(
                (params: Params) => {
                    this.id = +params['id'];
                    this.editMode = params['id'] != null;
                    this.initForm();
                }
            )
    }

    onCancel() {
        this.router.navigate(['../'], {relativeTo: this.route})
    }

    onSubmit() {
        const newRecipe = new Recipe(this.recipeForm.value['name'], this.recipeForm.value['description'], this.recipeForm.value['imagePath'],
            this.recipeForm.value['ingredients']);
        if (this.editMode) {
            this.recipeService.updateRecipe(this.id, newRecipe);
        } else {
            this.recipeService.addRecipe(newRecipe);
        }
        this.onCancel();
    }

    onAddIngredient() {
        (<FormArray>this.recipeForm.get('ingredients')).push(
            new FormGroup({
                'name': new FormControl(),
                'amount': new FormControl()
            })
        )
    }

    private initForm() {
        let recipeName = '';
        let recipeImagePath = '';
        let recipeDescription = '';
        let recipeIngredients = new FormArray([]);

        if (this.editMode) {
            const recipe = this.recipeService.getRecipe(this.id);
            recipeName = recipe.name;
            recipeImagePath = recipe.imagePath;
            recipeDescription = recipe.description;
            if (recipe['ingredientsList']) {
                for (let ingredient of recipe.ingredientsList) {
                    recipeIngredients.push(
                        new FormGroup({
                            'name': new FormControl(ingredient.name, Validators.required),
                            'amount': new FormControl(ingredient.amount, Validators.required)
                        })
                    );
                }
            }
        }
        this.recipeForm = new FormGroup({
            'name': new FormControl(recipeName, Validators.required),
            'imagePath': new FormControl(recipeImagePath, Validators.required),
            'description': new FormControl(recipeDescription, Validators.required),
            'ingredients': recipeIngredients

        })
    }

    takePicture() {
        const IMAGE_PREFIX = 'data:image/jpeg;base64,';
        Camera.getPhoto({resultType: CameraResultType.Base64}).then(
            photo => this.recipeForm.controls.imagePath.setValue(IMAGE_PREFIX + photo.base64String));
    }

    pickPicture(event) {
        if (event.target.files && event.target.files[0]) {
            if (event.target.files[0].type.match('image.*')) {
                const reader = new FileReader();
                reader.onload = () => this.recipeForm.controls.imagePath.setValue(reader.result.toString());
                reader.readAsDataURL(event.target.files[0]);
            } else {
                console.log('Wybrany plik nie jest zdjeciem');
            }
        }
    }
}
