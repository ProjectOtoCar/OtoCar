import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CustomValidators } from '../../validators/CustomValidators';
import { LoginUserService } from 'src/app/services/loginUser/login-user.service';
import { Router } from '@angular/router';
import { UserPageService } from 'src/app/services/user-page.service';
import { SellerPost } from 'src/app/interfaces/SellerPost.model';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  registrationForm: FormGroup;
  isAccept = false;
  isLoading = false;
  isError = false;
  isSuccess = false;
  constructor(
    private loginUserService: LoginUserService,
    private router: Router,
    private userPageService: UserPageService) { }

  ngOnInit(): void {
    this.registrationForm = new FormGroup({
      firstName: new FormControl(null,
        [
          Validators.required,
          Validators.maxLength(40),
          Validators.minLength(3),
          CustomValidators.withoutSpace
        ]),
        lastName: new FormControl(null,
        [
          Validators.required,
          Validators.maxLength(40),
          Validators.minLength(3),
          CustomValidators.withoutSpace
        ]),
        phoneNumber: new FormControl(null,
          [
            Validators.required,
            Validators.maxLength(9),
            CustomValidators.phoneNumber,
            CustomValidators.withoutSpace
          ]),
        registerUser: new FormGroup({
        username: new FormControl(null,
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
      })
    });
  }
  onClickAccept(): void {
    this.isAccept = !this.isAccept;
  }
  onSubmit(): void {
    this.isLoading = true;
    this.isError = false;
    this.isSuccess = false;
    this.loginUserService.createUser(this.registrationForm.value)
    .subscribe((user) => {
      this.userPageService.postSeller(
          {
            authId: user.id,
            firstName: this.registrationForm.value.firstName,
            lastName: this.registrationForm.value.lastName,
            phoneNumber: this.registrationForm.value.phoneNumber
          } as SellerPost)
          .subscribe(() => {
            this.isLoading = false;
            this.isSuccess = true;
          }, error => {
            this.isLoading = false;
            this.isError = true;
          });
      this.registrationForm.reset();
    }, error => {
      this.isLoading = false;
      this.isError = true;
    });
  }
  onPressSuccessModal(event: boolean): void {
    if (event) {
      this.router.navigate(['/']);
    }
  }
}
