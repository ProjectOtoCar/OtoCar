import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CustomValidators } from '../../validators/CustomValidators';
import { LoginUserService } from 'src/app/services/loginUser/login-user.service';
import { Router } from '@angular/router';

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
  constructor(private loginUserService: LoginUserService, private router: Router) { }

  ngOnInit(): void {
    this.registrationForm = new FormGroup({
      firstName: new FormControl(null,
        [
          Validators.required,
          Validators.maxLength(40),
          Validators.minLength(3),
          CustomValidators.withoutSpace
        ]),
      lastname: new FormControl(null,
        [
          Validators.required,
          Validators.maxLength(40),
          Validators.minLength(3),
          CustomValidators.withoutSpace
        ]),
      userData: new FormGroup({
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
    this.loginUserService.createUser(this.registrationForm.value.userData)
    .subscribe((user) => {
      this.isLoading = false;
      this.isSuccess = true;
      this.registrationForm.reset();
      console.log(user);
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
