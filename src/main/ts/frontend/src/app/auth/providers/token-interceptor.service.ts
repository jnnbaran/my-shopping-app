import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenModel} from "../models/token.model";

@Injectable()
export class TokenInterceptorService implements HttpInterceptor {

    constructor() { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let token = localStorage.getItem('access_token');

        if(token) {
            req = req.clone({
                setHeaders: {
                    Authorization: "Bearer " + (new TokenModel().merge(JSON.parse(token))).token
                }
            });
        }
        return next.handle(req);
    }
}
