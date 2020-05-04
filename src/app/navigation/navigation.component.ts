import { Component, OnInit } from '@angular/core';
import { LoginUserService } from '../services/loginUser/login-user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  navbarOpen = false;
  constructor(
    private loginUserService: LoginUserService,
    private route: Router
    ) { }

  toggleNavbar() {
    this.navbarOpen = !this.navbarOpen;
  }
  ngOnInit(): void {
  }

  signOut(): void {
    this.loginUserService.signOut();
    this.route.navigate(['/']);
  }

}
