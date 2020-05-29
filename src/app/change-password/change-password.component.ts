import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { CustomValidators } from '../validators/CustomValidators';
import { ResetPasswordComponent } from '../reset-password/reset-password.component';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {
  resetPasswordForm: FormGroup;
  isChangePassword = false;
  isLoading = false;
  isError = false;
  token: string;
  
  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private resetPasswordService: ResetPasswordComponent) {
    this.resetPasswordForm = new FormGroup({
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

  ngOnInit(): void {
    this.activatedRoute.queryParams
    .subscribe((params: Params) => {
      this.token = params.token;
    });
  }

  onSubmit(): void {

  }
  onChangePassword(): void {
    this.router.navigate(['/sign-in']);
  }
}
