import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CustomValidators} from '../../validators/CustomValidators';
import { Contact } from '../../interfaces/Contact';
import { EmailSenderService } from '../../services/emailSender/email-sender.service';
import { LoginUserService } from '../../services/loginUser/login-user.service';
import { environment } from 'src/environments/environment';
import { LoginUser } from '../../interfaces/loginUser.model';

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
  constructor(
    private emailSenderService: EmailSenderService,
    private loginUserService: LoginUserService) { }

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
        mailSender: new FormControl({value: null,  disabled: false},
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

    this.loginUserService.loginUser
      .subscribe((loginUser: LoginUser) => {
        if (loginUser !== undefined && loginUser !== null) {
          this.loginForm.patchValue({
            mailSender: loginUser.email
          });
          this.loginForm.get('mailSender').disable();
        }
      });

  }
  onSubmit(): void {
    this.isSend = false;
    this.isLoading = true;
    this.isError = false;
    this.loginForm.get('mailSender').enable();
    const sendEmail: Contact = {...this.loginForm.value, mail: environment.contactEmail};
    this.loginForm.get('mailSender').disable();
    this.emailSenderService.sendEmail(sendEmail).subscribe(() => {
      this.isLoading = false;
      this.loginForm.reset();
      this.loginForm.patchValue({
        mail: sendEmail.mail,
        mailSender: sendEmail.mailSender
      });
      this.isSend = true;
    }, error => {
      this.isError = true;
      this.isLoading = false;
    });
  }

}
