import { Component, OnInit } from '@angular/core';
import {Recipe} from '../recipe.model'

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipes: Recipe [] = [
    new Recipe('A test recipe', 'This is a simple test', 'https://img.taste.com.au/Pas8_G-H/w643-h428-cfill-q90/taste/2019/04/healthy-chilli-con-carne-148825-1.jpg')
  ]
  constructor() { }

  ngOnInit(): void {
    console.log("siemanko");
    this.recipes.forEach(console.log);
  }

}
