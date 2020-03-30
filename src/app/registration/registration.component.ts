import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CustomValidators } from '../validators/CustomValidators';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  registrationForm: FormGroup;
  isAccept = false;
  constructor() { }

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
      })
    });
  }
  onClickAccept(): void {
    console.log(this.isAccept);
    this.isAccept = !this.isAccept;
  }
  onSubmit(): void {
    console.log(this.registrationForm);
    // this.registrationForm.reset();
  }

}
