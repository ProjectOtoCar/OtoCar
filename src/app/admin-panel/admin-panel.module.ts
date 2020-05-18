import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { SearchFormComponent } from './search-form/search-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { PipeModule } from '../pipe-module/pipe.module';
import { ModalModule } from '../modal/modal.module';
import { PaginationModule } from '../pagination/pagination.module';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [
    AdminPanelComponent,
    SearchFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    PipeModule,
    ModalModule,
    PaginationModule,
    SharedModule,
    AppRoutingModule
  ], exports: [
    AdminPanelComponent
  ]
})
export class AdminPanelModule { }
