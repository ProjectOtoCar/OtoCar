import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OneAdvertismentComponent } from './one-advertisment/one-advertisment.component';
import { SharedModule } from '../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ModalModule } from '../modal/modal.module';



@NgModule({
  declarations: [
    OneAdvertismentComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ModalModule,
    NgbModule
  ],
  exports: [
    OneAdvertismentComponent
  ]
})
export class OneAdvertismentModule { }
