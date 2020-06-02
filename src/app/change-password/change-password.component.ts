import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { CustomValidators } from '../validators/CustomValidators';
import { ResetPasswordService } from '../services/reset-password/reset-password.service';

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
    private resetPasswordService: ResetPasswordService,
    private activatedRoute: ActivatedRoute,
    private router: Router
    ) {
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
    this.isChangePassword = false;
    this.isLoading = false;
    this.isError = false;
    this.resetPasswordService.changePassword(this.token, this.resetPasswordForm.value)
      .subscribe(() => {
        this.isLoading = false;
        this.isChangePassword = true;
      }, error => {
        this.isError = true;
        this.isLoading = false;
      });
  }
  onChangePassword(): void {
    this.router.navigate(['/sign-in']);
  }
}
