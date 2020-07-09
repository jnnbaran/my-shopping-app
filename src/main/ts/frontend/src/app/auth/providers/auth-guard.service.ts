import { Injectable } from '@angular/core';

import {Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree} from '@angular/router';
import { AuthService } from './auth.service';
import {Observable} from "rxjs";
import {map, take} from "rxjs/operators";


@Injectable({
    providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

    constructor(public auth: AuthService, public router: Router) {}

    canActivate(     route: ActivatedRouteSnapshot,
                     state: RouterStateSnapshot
    ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        return this.auth.isLoginIn.pipe(
            take(1),
            map(isLoginIn => {
                return isLoginIn ? isLoginIn : this.router.createUrlTree(['/login']);
            }));
    }
}