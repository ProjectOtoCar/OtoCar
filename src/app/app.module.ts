import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
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
    ErrorComponent

  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
