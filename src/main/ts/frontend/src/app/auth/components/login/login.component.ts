import {Component, OnInit} from '@angular/core';

import {FormGroup} from '@angular/forms';
import {AuthService} from '../../providers/auth.service';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
    loginSub: Subscription;
    registerSub: Subscription;
    loginForm: FormGroup;

    constructor(private authService: AuthService, private router: Router) {
    }

    login(formData) {
        this.loginSub = this.authService.login(formData.value)
            .subscribe(() => this.router.navigate(['recipes']),
                error => console.log(error));
    }

    register(formData) {
        this.registerSub = this.authService.register(formData.value)
            .subscribe(() => console.log('udalo sie zalogować i zarejstrować!'),
                error => console.log(error));

    }

    logout() {
        this.authService.logout();
    }

    test() {
        this.authService.test().subscribe(json => alert(json.test));
    }

   // ngOnDestroy(): void {
    //    this.loginSub.unsubscribe();
    //    this.registerSub.unsubscribe();
   // }

    ngOnInit() {
    }

}
