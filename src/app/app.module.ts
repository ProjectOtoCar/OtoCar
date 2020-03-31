import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UserPageComponent } from './user-page/user-page.component';
import { MainPageComponent } from './main-page/main-page.component';
import { NavigationComponent } from './navigation/navigation.component';
import { RegistrationComponent } from './registration/registration.component';
import { ItemComponent } from './user-page/item/item.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { ItemAdminPanelComponent } from './admin-panel/item-admin-panel/item-admin-panel.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { ErrorComponent } from './error/error.component';
import { UserDetailsComponent } from './user-page/user-details/user-details.component';
import { FooterComponent } from './footer/footer.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { MainstatuteComponent } from './main-statute/main-statute.component';
import { CookiePolicyComponent } from './cookie-policy/cookie-policy.component';
import { PrivacyPolicyComponent } from './privacy-policy/privacy-policy.component';
import { BusinessStatuteComponent } from './business-statute/business-statute.component';

@NgModule({
  declarations: [
    AppComponent,
    UserPageComponent,
    MainPageComponent,
    NavigationComponent,
    RegistrationComponent,
    ItemComponent,
    AdminPanelComponent,
    ItemAdminPanelComponent,
    SignInComponent,
    ErrorComponent,
    UserDetailsComponent,
    FooterComponent,
    SpinnerComponent,
    MainstatuteComponent,
    CookiePolicyComponent,
    PrivacyPolicyComponent,
    BusinessStatuteComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
