import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "../validators/CustomValidators";

@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.scss']
})
export class ContactFormComponent implements OnInit {

  loginForm: FormGroup;
  constructor() { }


  ngOnInit(): void {
    this.loginForm = new FormGroup({
      firstName: new FormControl(null,
        [
          Validators.required,
          Validators.maxLength(2),
          Validators.minLength(30),
          CustomValidators.withoutSpace
        ]),
      email: new FormControl(null,
        [
          Validators.required,
          Validators.email,
          Validators.maxLength(100),
          Validators.minLength(3),
          CustomValidators.withoutSpace
        ]),
      title: new FormControl(null,
        [
          Validators.required,
          Validators.maxLength(5),
          Validators.minLength(40),
        ]),
      content: new FormControl(null,
        [
          Validators.required,
          Validators.minLength(20),
          Validators.maxLength(200),
        ])
    });

  }
  onSubmit(): void {
    console.log(this.loginForm.value);
  }

}
