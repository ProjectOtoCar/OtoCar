import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorMessageComponent } from './error-message/error-message.component';
import { AppRoutingModule } from '../app-routing.module';
import { PipeModule } from '../pipe-module/pipe.module';
import { TosterComponent } from './toster/toster.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { SuccessMessageComponent } from './success-message/success-message.component';
import { TelLinkComponent } from './tel-link/tel-link.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SearchAddFormComponent } from './search-add-form/search-add-form.component';


@NgModule({
  declarations: [
    ErrorMessageComponent,
    SuccessMessageComponent,
    TosterComponent,
    SpinnerComponent,
    TelLinkComponent,
    SearchAddFormComponent
  ],
  imports: [
    CommonModule,
    PipeModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  exports: [
    ErrorMessageComponent,
    SuccessMessageComponent,
    TosterComponent,
    SpinnerComponent,
    TelLinkComponent,
    SearchAddFormComponent
  ],
})
export class SharedModule { }
