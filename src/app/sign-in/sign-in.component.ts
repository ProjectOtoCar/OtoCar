import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CustomValidators } from '../validators/CustomValidators';
import { LoginUserService } from '../services/loginUser/login-user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  loginForm: FormGroup;
  isLoginSuccess = false;
  constructor(
    private loginUserService: LoginUserService,
    private route: Router
    ) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl(null,
        [
          Validators.required,
          Validators.email,
          Validators.maxLength(100),
          Validators.minLength(3),
          CustomValidators.withoutSpace
        ]),
      password: new FormControl(null,
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(40),
          CustomValidators.requiredCapitalLetter,
          CustomValidators.requiredDigit,
          CustomValidators.requiredSpecialSign,
          CustomValidators.withoutSpace
        ])
    });

  }

  onSubmit(): void {
    this.loginUserService.signIn(this.loginForm.value);
    this.isLoginSuccess = true;
  }

  onLoginSuccess(): void {
    this.route.navigate(['/']);
  }

}
