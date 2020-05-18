import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShowAdvertismentComponent } from './show-advertisment/show-advertisment.component';
import { PaginationModule } from '../pagination/pagination.module';
import { SharedModule } from '../shared/shared.module';
import { FiltrAdvertismentComponent } from '../filtr-advertisment/filtr-advertisment.component';



@NgModule({
  declarations: [
    ShowAdvertismentComponent,
    FiltrAdvertismentComponent
  ],
  imports: [
    CommonModule,
    PaginationModule,
    SharedModule
  ],
  exports: [
    ShowAdvertismentComponent
  ]
})
export class ShowAdvertismentModule { }
