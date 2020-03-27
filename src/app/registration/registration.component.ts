import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  registrationForm: FormGroup;
  constructor() { }

  ngOnInit(): void {
    this.registrationForm = new FormGroup({
      firstName: new FormControl(null, Validators.required),
      lastname: new FormControl(null, Validators.required),
      userData: new FormGroup({
        email: new FormControl(null, Validators.required),
        password: new FormControl(null, Validators.required)
      })
    });
  }

  onSubmit(): void {
    console.log(this.registrationForm.value);
    this.registrationForm.reset();
  }

}
