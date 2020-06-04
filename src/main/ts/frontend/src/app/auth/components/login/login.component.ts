import { Component, OnInit } from '@angular/core';


import {FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import {AuthService} from "../../auth.service";
import {HttpHeaders} from "@angular/common/http";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
  }

  login(formData){
    this.authService.login(formData.value);

  }

  signup(formData: NgForm){
    this.authService.signup(formData.value.email, formData.value.password);

  }
}
