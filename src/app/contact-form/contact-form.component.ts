import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "../validators/CustomValidators";
import { ContactFormService } from '../services/contact-form.service';
import { Contact } from '../interfaces/Contact';

@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.scss']
})
export class ContactFormComponent implements OnInit {
  loginForm: FormGroup;
  isLoading: boolean;
  isSend: boolean;
  isError: boolean;
  constructor(private contactFormService: ContactFormService) { }

  ngOnInit(): void {
    this.isSend = false;
    this.isLoading = false;
    this.isError = false;
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
    this.isSend = false;
    this.isLoading = true;
    this.isError = false;
    const sendEmail: Contact = {...this.loginForm.value, mail: 'ToJestMailDoProjektuOtoCar@gmail.com'};
    this.contactFormService.sendEmail(sendEmail).subscribe(() => {
      this.isLoading = false;
      this.loginForm.reset();
      this.isSend = true;
    }, error => {
      this.isError = true;
      this.isLoading = false;
    });
  }

}
