import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { MainPageComponent } from './main-page/main-page.component';
import { NavigationComponent } from './navigation/navigation.component';
import { ErrorComponent } from './error/error.component';

import { FooterComponent } from './footer/footer.component';
import { SharedModule } from './shared/shared.module';
import { ModalModule } from './modal/modal.module';
import { PaginationModule } from './pagination/pagination.module';
import { StatuteModule } from './status/statute.module';
import { UserPageModule } from './user-page/user-page.module';
import { AdminPanelModule } from './admin-panel/admin-panel.module';
import { RegistrationModule } from './registration/registration.module';
import { SignInModule } from './sign-in/sign-in.module';
import { OneAdvertismentModule } from './one-advertisment/one-advertisment.module';
import { ShowAdvertismentModule } from './show-advertisment/show-advertisment.module';
import { AdvertismentModifyModule } from './advertisment-modify/advertisment-modify.module';
import { ContactFormModule } from './contact-form/contact-form.module';
import { AuthInterceptorService } from './interceptors/auth-interceptor.service';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { VerifyAccountComponent } from './verify-account/verify-account.component';




@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    NavigationComponent,
    ErrorComponent,
    FooterComponent,
    ResetPasswordComponent,
    ChangePasswordComponent,
    VerifyAccountComponent,
  ],
  imports: [
    BrowserModule,
    SharedModule,
    ModalModule,
    PaginationModule,
    StatuteModule,
    UserPageModule,
    AdminPanelModule,
    RegistrationModule,
    SignInModule,
    OneAdvertismentModule,
    ShowAdvertismentModule,
    AdvertismentModifyModule,
    ContactFormModule,
    HttpClientModule,
    BrowserAnimationsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
