import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdvertismentModifyComponent } from './advertisment-modify/advertisment-modify.component';
import { SharedModule } from '../shared/shared.module';
import { ModalModule } from '../modal/modal.module';



@NgModule({
  declarations: [
    AdvertismentModifyComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ModalModule
  ],
  exports: [
    AdvertismentModifyComponent
  ]
})
export class AdvertismentModifyModule { }
