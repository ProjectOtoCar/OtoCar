import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {NgbCarouselModule, NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UserPageComponent } from './user-page/user-page.component';
import { MainPageComponent } from './main-page/main-page.component';
import { NavigationComponent } from './navigation/navigation.component';
import { RegistrationComponent } from './registration/registration.component';
import { ItemComponent } from './user-page/item/item.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { ErrorComponent } from './error/error.component';
import { UserDetailsComponent } from './user-page/user-details/user-details.component';
import { FooterComponent } from './footer/footer.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { MainstatuteComponent } from './main-statute/main-statute.component';
import { CookiePolicyComponent } from './cookie-policy/cookie-policy.component';
import { PrivacyPolicyComponent } from './privacy-policy/privacy-policy.component';
import { BusinessStatuteComponent } from './business-statute/business-statute.component';
import { OneAdvertismentComponent } from './one-advertisment/one-advertisment.component';
import { PaginationComponent } from './pagination/pagination.component';
import { PaginationItemComponent } from './pagination/pagination-item/pagination-item.component';
import { ContactFormComponent } from './contact-form/contact-form.component';
import { SearchFormComponent } from './admin-panel/search-form/search-form.component';
import { UpperCaseFirstLetterPipe } from './pipes/upper-case-first-letter.pipe';
import { UserDataComponent } from './user-page/user-details/user-data/user-data.component';
import { FiltrAdvertismentComponent } from './filtr-advertisment/filtr-advertisment.component';
import { ModalComponent } from './modal/modal.component';
import { ModifyProfileComponent } from './user-page/user-details/modify-profile/modify-profile.component';
import { AddAddvertismentComponent } from './user-page/user-details/add-addvertisment/add-addvertisment.component';
import { UserAddvertismentsComponent } from './user-page/user-details/user-addvertisments/user-addvertisments.component';
import { UserAddvertismentItemComponent } from './user-page/user-details/user-addvertisments/user-addvertisment-item/user-addvertisment-item.component';


@NgModule({
  declarations: [
    AppComponent,
    UserPageComponent,
    MainPageComponent,
    NavigationComponent,
    RegistrationComponent,
    ItemComponent,
    AdminPanelComponent,
    SignInComponent,
    ErrorComponent,
    UserDetailsComponent,
    FooterComponent,
    SpinnerComponent,
    MainstatuteComponent,
    CookiePolicyComponent,
    PrivacyPolicyComponent,
    BusinessStatuteComponent,
    OneAdvertismentComponent,
    PaginationItemComponent,
    PaginationComponent,
    ContactFormComponent,
    SearchFormComponent,
    UpperCaseFirstLetterPipe,
    UserDataComponent,
    ModalComponent,
    ModifyProfileComponent,
    FiltrAdvertismentComponent,
    AddAddvertismentComponent,
    UserAddvertismentsComponent,
    UserAddvertismentItemComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
