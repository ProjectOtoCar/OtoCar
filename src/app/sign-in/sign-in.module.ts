import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { SignInComponent } from './sign-in/sign-in.component';
import { ModalModule } from '../modal/modal.module';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [
    SignInComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ModalModule,
    SharedModule
  ],
  exports: [
    SignInComponent
  ]
})
export class SignInModule { }
