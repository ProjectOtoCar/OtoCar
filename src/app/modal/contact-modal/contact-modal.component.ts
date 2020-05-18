import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Seller } from '../../interfaces/Seller';
import { CustomValidators } from '../../validators/CustomValidators';
import { EmailSenderService } from '../../services/emailSender/email-sender.service';
import { Contact } from '../../interfaces/Contact';
import { LoginUserService } from '../../services/loginUser/login-user.service';
import { LoginUser } from '../../interfaces/loginUser.model';

@Component({
  selector: 'app-contact-modal',
  templateUrl: './contact-modal.component.html',
  styleUrls: ['./contact-modal.component.scss']
})
export class ContactModalComponent implements OnInit {

  @Input() seller: Seller;
  @Output() closeModal = new EventEmitter<boolean>();
  isLoading = false;
  isError = false;
  isSend = false;
  contactForm: FormGroup;
  constructor(
    private emailSenderService: EmailSenderService,
    private loginUserService: LoginUserService) {
    this.contactForm = new FormGroup({
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
    if(!this.seller?.user?.email) {
      this.isError = true;
      this.isLoading = false;
      this.isSend = false;
      return;
    }
    const sendEmail: Contact = {...this.contactForm.value, mail: this.seller?.user.email};
    this.emailSenderService.sendEmail(sendEmail).subscribe(() => {
      this.isLoading = false;
      this.contactForm.reset();
      this.isSend = true;
    }, error => {
      this.isError = true;
      this.isLoading = false;
    });
   }
  ngOnInit(): void {
    this.loginUserService.loginUser
      .subscribe((loginUser: LoginUser) => {
        if (loginUser !== undefined && loginUser !== null) {
          this.contactForm.patchValue({
            mailSender: loginUser.email
          });
          this.contactForm.get('mailSender').disable();
        }
      });
  }


  onCloseModal(): void {
    this.closeModal.emit(true);
  }
}
