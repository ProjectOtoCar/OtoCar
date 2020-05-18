import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { SearchFormComponent } from './search-form/search-form.component';
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
    ModalModule,
    PaginationModule,
    SharedModule,
  ], exports: [
    AdminPanelComponent
  ]
})
export class AdminPanelModule { }
