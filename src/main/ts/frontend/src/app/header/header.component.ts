import {Component, OnInit} from '@angular/core';
import {DataStorageService} from './shared/data-storage.service';
import {AuthService} from '../auth/providers/auth.service';
import {PopoverController} from '@ionic/angular';
import {PopoverComponent} from './popover/popover.component';
import {Router} from '@angular/router';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
    collapsed = true;
    loggedIn;

    constructor(
        private auth: AuthService,
        private dataStorageService: DataStorageService,
        private popoverController: PopoverController,
        private router: Router
    ) {
    }

    ngOnInit(): void {
        this.auth.isLoginIn.subscribe(loggedIn => this.loggedIn = loggedIn);
    }

    logout() {
        this.auth.logout();
        this.router.navigate(['home']);
    }

    async presentPopover(ev: any) {
        const popover = await this.popoverController.create({
            component: PopoverComponent,
            cssClass: 'my-custom-class',
            event: ev,
            translucent: true
        });
        return await popover.present();
    }
}
