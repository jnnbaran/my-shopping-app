import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})

export class AuthService {


  constructor(private http: HttpClient) { }

  login(formValue) {
    this.http.post("http://localhost:8080/api/login",formValue).subscribe();
  }

  logout(){

  }

  register(formValue) {
    this.http.post("http://localhost:8080/api/register",formValue).subscribe();
  }
}
