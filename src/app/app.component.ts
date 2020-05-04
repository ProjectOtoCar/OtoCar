import { Component, OnInit } from '@angular/core';
import { LoginUserService } from './services/loginUser/login-user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'otoCar';
  constructor(private loginUserService: LoginUserService) { }

  ngOnInit(): void {
    this.loginUserService.autoSignIn();
  }
}
