import { Component, OnInit, OnDestroy } from '@angular/core';
import { LoginUserService } from '../services/loginUser/login-user.service';
import { Router } from '@angular/router';
import { LoginUser } from '../interfaces/loginUser.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit, OnDestroy {
  navbarOpen = false;
  user: LoginUser;
  loginUserSub: Subscription;
  constructor(
    private loginUserService: LoginUserService,
    private route: Router
    ) { }

  toggleNavbar() {
    this.navbarOpen = !this.navbarOpen;
  }
  ngOnInit(): void {
    this.loginUserSub = this.loginUserService.loginSelerId
      .subscribe((loginUser: LoginUser) => {
        this.user = loginUser;
      });
  }

  signOut(): void {
    this.loginUserService.signOut();
    this.route.navigate(['/']);
  }

  ngOnDestroy(): void {
    this.loginUserSub?.unsubscribe();
  }
}
