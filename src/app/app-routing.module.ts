import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserPageComponent } from './user-page/user-page.component';
import { MainPageComponent } from './main-page/main-page.component';
import { RegistrationComponent } from './registration/registration.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { ErrorComponent } from './error/error.component';
import { UserDetailsComponent } from './user-page/user-details/user-details.component';
import {MainstatuteComponent} from "./main-statute/main-statute.component";
import {BusinessStatuteComponent} from "./business-statute/business-statute.component";
import {PrivacyPolicyComponent} from "./privacy-policy/privacy-policy.component";
import {CookiePolicyComponent} from "./cookie-policy/cookie-policy.component";
import {OneAdvertismentComponent} from "./one-advertisment/one-advertisment.component";
import {ContactFormComponent} from "./contact-form/contact-form.component";



const routes: Routes = [
  {path: '', component: MainPageComponent, pathMatch: 'full'},
  {path: 'user-page', component: UserDetailsComponent},
  {path: 'buy-premium', component: UserPageComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'admin', component: AdminPanelComponent},
  {path: 'sign-in', component: SignInComponent},
  {path: 'mainStatute', component: MainstatuteComponent},
  {path: 'businessStatute', component: BusinessStatuteComponent},
  {path: 'privacyPolicy', component: PrivacyPolicyComponent},
  {path: 'cookiePolicy', component: CookiePolicyComponent},
  {path: 'one', component: OneAdvertismentComponent},
  {path: 'contact', component: ContactFormComponent},
  {path: 'not-found', component: ErrorComponent, data: {message: 'Nic tutaj nie ma', error: 404, subMessage: 'strona nie znaleziona!'}},
  {path: '**', redirectTo: '/not-found'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
