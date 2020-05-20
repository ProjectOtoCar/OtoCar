import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserPageComponent } from './user-page/user-page.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserAddvertismentsComponent } from './user-addvertisments/user-addvertisments.component';
import { AddAddvertismentComponent } from './add-addvertisment/add-addvertisment.component';
import { ModifyProfileComponent } from './modify-profile/modify-profile.component';
import { UserDataComponent } from './user-data/user-data.component';
import { ItemComponent } from './item/item.component';
import { HttpClientModule } from '@angular/common/http';
import { ModalModule } from '../modal/modal.module';
import { SharedModule } from '../shared/shared.module';
import { PaginationModule } from '../pagination/pagination.module';
import { UserAddvertismentItemComponent } from './user-addvertisment-item/user-addvertisment-item.component';



@NgModule({
  declarations: [
    UserPageComponent,
    UserDetailsComponent,
    UserAddvertismentsComponent,
    UserAddvertismentItemComponent,
    AddAddvertismentComponent,
    ModifyProfileComponent,
    UserDataComponent,
    ItemComponent,
  ],
  imports: [
    CommonModule,
    ModalModule,
    PaginationModule,
    SharedModule,
    HttpClientModule,
  ],
  exports: [
    UserPageComponent,
    UserDetailsComponent,
    UserAddvertismentsComponent,
    AddAddvertismentComponent,
    ModifyProfileComponent
  ]
})
export class UserPageModule { }
