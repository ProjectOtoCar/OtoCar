import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaginationItemComponent } from './pagination-item/pagination-item.component';
import { PaginationComponent } from './pagination/pagination.component';
import { AppRoutingModule } from '../app-routing.module';



@NgModule({
  declarations: [
    PaginationItemComponent,
    PaginationComponent,
  ],
  imports: [
    CommonModule,
    AppRoutingModule
  ],
  exports: [PaginationComponent]
})
export class PaginationModule { }
