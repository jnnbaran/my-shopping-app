import { Component, OnInit } from '@angular/core';


import {FormGroup} from "@angular/forms";
import {AuthService} from "../../auth.service";


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

  register(formData) {
    this.authService.register(formData.value);
  }
}
