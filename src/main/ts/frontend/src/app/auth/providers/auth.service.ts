import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TokenModel} from "../models/token.model";
import {BehaviorSubject, Observable} from "rxjs";
import {switchMap, tap, timeout} from "rxjs/operators";


@Injectable({
    providedIn: 'root'
})

export class AuthService {

    private _isLoginIn = new BehaviorSubject(false);
    get isLoginIn(): Observable<boolean> {
        return this._isLoginIn.asObservable();
    }

    constructor(private http: HttpClient) {
    }

    register(formValue) {
        return this.http.post<string>("http://localhost:8080/api/auth/register", formValue)
            .pipe(switchMap(() => this.login(formValue)));
    }

    login(formValue) {
        return this.http.post<TokenModel>("http://localhost:8080/api/auth/login", formValue)
            .pipe(
                timeout(2000),
                tap(token => this.addAccessToken(new TokenModel().merge(token))),
            );
    }

    logout() {
        localStorage.removeItem('access_token');
        this._isLoginIn.next(false);
    }

    addAccessToken(token: TokenModel) {
        localStorage.setItem('access_token', JSON.stringify(token));
        this._isLoginIn.next(true);
    }

    test() {
       return  this.http.post<any>("http://localhost:8080/api/recipe/test", {})
    }
}
