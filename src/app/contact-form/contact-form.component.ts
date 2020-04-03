import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "../validators/CustomValidators";
import { Contact } from '../interfaces/Contact';
import { ContactFormService } from '../services/contact-form.service';

@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.scss']
})
export class ContactFormComponent implements OnInit {
  loginForm: FormGroup;
  isLoading: boolean;
  isError: boolean;
  constructor(private contactFormService: ContactFormService) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      title: new FormControl(null,
        [
          Validators.required,
          Validators.maxLength(40),
          Validators.minLength(5),
        ]),
        mailSender: new FormControl(null,
        [
          Validators.required,
          Validators.email,
          Validators.maxLength(100),
          Validators.minLength(3),
          CustomValidators.withoutSpace
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
    this.isLoading = true;
    this.isError = false;
    const sendEmail: Contact = {...this.loginForm.value, mail: 'ToJestMailDoProjektuOtoCar@gmail.com'};
    console.log(sendEmail);
    this.contactFormService.sendEmail(sendEmail).subscribe(() => {
      this.isLoading = false;
    }, error => {
      this.isError = true;
      this.isLoading = false;
    });
  }

}
