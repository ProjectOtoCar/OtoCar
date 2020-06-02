import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CustomValidators } from '../../validators/CustomValidators';
import { LoginUserService } from '../../services/loginUser/login-user.service';
import { Router } from '@angular/router';
import { CustomAsyncValidators } from 'src/app/validators/CustomAsyncValidators';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  loginForm: FormGroup;
  isLoginSuccess = false;
  isLoading = false;
  isError = false;
  isShowEnabledModal = false;
  constructor(
    private loginUserService: LoginUserService,
    private route: Router
    ) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(null,
        [
          Validators.required,
          Validators.email,
          Validators.maxLength(100),
          Validators.minLength(3),
          CustomValidators.withoutSpace
        ], [
          CustomAsyncValidators.isUsernameNotExisted(this.loginUserService)
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
    this.isError = false;
    this.isLoading = false;
    this.isLoginSuccess = false;
    this.isShowEnabledModal = false;
    this.loginUserService.signIn(this.loginForm.value)
      .subscribe((observable) => {
        observable.subscribe((data) => {
          data.subscribe((isEnabled: boolean) => {
            this.isLoading = false;
            if (isEnabled) {
                this.isLoginSuccess = true;
              } else {
                this.isShowEnabledModal = true;
              }
          });
        });
      }, error => {
        console.log(error);
        this.isLoading = false;
        this.isError = true;
      });
  }

  onLoginSuccess(): void {
    this.route.navigate(['/']);
  }

  onIsNotEnabled(): void {
    this.isShowEnabledModal = false;
  }

}
