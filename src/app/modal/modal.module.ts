import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalComponent } from './modal/modal.component';
import { ImageModalComponent } from './image-modal/image-modal.component';
import { SharedModule } from '../shared/shared.module';
import { ContactModalComponent } from './contact-modal/contact-modal.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PipeModule } from '../pipe-module/pipe.module';



@NgModule({
  declarations: [
    ModalComponent,
    ImageModalComponent,
    ContactModalComponent
  ],
  imports: [
    CommonModule,
    PipeModule,
    SharedModule,
    ReactiveFormsModule,
  ],
  exports: [
    ModalComponent,
    ImageModalComponent,
    ContactModalComponent
  ]
})
export class ModalModule { }
