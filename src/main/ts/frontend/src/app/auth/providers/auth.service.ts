import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TokenModel} from '../models/token.model';
import {BehaviorSubject, Observable} from 'rxjs';
import {switchMap, tap, timeout} from 'rxjs/operators';
import {environment} from '../../../environments/environment';


@Injectable({
    providedIn: 'root'
})

export class AuthService {

    private _isLoginIn = new BehaviorSubject(false);
    private tokenExpirationTimer: number;

    get isLoginIn(): Observable<boolean> {
        return this._isLoginIn.asObservable();
    }

    constructor(private http: HttpClient) {
        if (localStorage.getItem('access_token')) {
            const token: TokenModel = new TokenModel().merge(JSON.parse(localStorage.getItem('access_token')));
            if (token.hasNotExpired()) {
                this._isLoginIn.next(true);
                this.addAutoLogout(token.expirationDuration());
            } else {
                this.logout();
            }
        }
    }

    register(formValue) {
        return this.http.post<string>(environment.api + 'auth/register', formValue)
            .pipe(switchMap(() => this.login(formValue)));
    }

    login(formValue) {
        return this.http.post<TokenModel>(environment.api + 'auth/login', formValue)
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
       return  this.http.post<any>(environment.api + 'recipe/test', {});
    }

    addAutoLogout(expirationDuration: number) {
        this.tokenExpirationTimer = setTimeout(() => {
            this.logout();
        }, expirationDuration);
    }
}
