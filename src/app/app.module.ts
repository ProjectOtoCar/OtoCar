import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MainPageComponent } from './main-page/main-page.component';
import { NavigationComponent } from './navigation/navigation.component';
import { RegistrationComponent } from './registration/registration.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { ErrorComponent } from './error/error.component';

import { FooterComponent } from './footer/footer.component';
import { OneAdvertismentComponent } from './one-advertisment/one-advertisment.component';
import { ContactFormComponent } from './contact-form/contact-form.component';
import { FiltrAdvertismentComponent } from './filtr-advertisment/filtr-advertisment.component';
import { AdvertismentModifyComponent } from './advertisment-modify/advertisment-modify.component';
import { ShowAdvertismentComponent } from './show-advertisment/show-advertisment.component';
import { SharedModule } from './shared/shared.module';
import { PipeModule } from './pipe-module/pipe.module';
import { ModalModule } from './modal/modal.module';
import { PaginationModule } from './pagination/pagination.module';
import { StatuteModule } from './status/statute.module';
import { UserPageModule } from './user-page/user-page.module';
import { AdminPanelModule } from './admin-panel/admin-panel.module';




@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    NavigationComponent,
    RegistrationComponent,
    SignInComponent,
    ErrorComponent,
    FooterComponent,
    OneAdvertismentComponent,
    ContactFormComponent,
    FiltrAdvertismentComponent,
    AdvertismentModifyComponent,
    ShowAdvertismentComponent,
  ],
  imports: [
    BrowserModule,
    SharedModule,
    PipeModule,
    ModalModule,
    PaginationModule,
    StatuteModule,
    UserPageModule,
    AdminPanelModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
