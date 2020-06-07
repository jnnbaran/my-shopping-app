import {Component, OnDestroy} from '@angular/core';

import {FormGroup} from "@angular/forms";
import {AuthService} from "../../providers/auth.service";
import {Subscription} from "rxjs";


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnDestroy {
    loginSub: Subscription;
    registerSub: Subscription;
    loginForm: FormGroup;

    constructor(private authService: AuthService) {
    }

    login(formData) {
        this.loginSub = this.authService.login(formData.value)
            .subscribe(() => console.log("udalo sie zalogować!"),
                error => console.log(error));
    }

    register(formData) {
        this.registerSub = this.authService.register(formData.value)
            .subscribe(() => console.log("udalo sie zalogować i zarejstrować!"),
                error => console.log(error))
    }

    logout() {
        this.authService.logout();
    }

    test() {
        this.authService.test().subscribe(json => alert(json.test));
    }

    ngOnDestroy(): void {
        this.loginSub.unsubscribe();
        this.registerSub.unsubscribe();
    }
}
