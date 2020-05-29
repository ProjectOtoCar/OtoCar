import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomValidators } from '../validators/CustomValidators';
import { ResetPasswordService } from '../services/reset-password/reset-password.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {
  resetPasswordForm: FormGroup;
  isEmailSend = false;
  isLoading = false;
  isError = false;
  constructor(
    private route: Router,
    private resetPasswordService: ResetPasswordService
  ) { }

  ngOnInit(): void {
    this.resetPasswordForm = new FormGroup({
      username: new FormControl(null,
      [
        Validators.required,
        Validators.email,
        Validators.maxLength(100),
        Validators.minLength(3),
        CustomValidators.withoutSpace
      ])
    });
  }

  onSubmit(): void {
    this.isError = false;
    this.isEmailSend = false;
    this.isLoading = true;
    this.resetPasswordService
      .sendEmailWithUsername(this.resetPasswordForm.value.username)
      .subscribe(() => {
        this.isEmailSend = true;
        this.isLoading = false;
      }, error => {
        this.isError = true;
        this.isLoading = false;
      })
  }
  onSendEmail(): void {
    this.route.navigate(['/sign-in']);
  }
}
