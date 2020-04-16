import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserPageComponent } from './user-page/user-page.component';
import { MainPageComponent } from './main-page/main-page.component';
import { RegistrationComponent } from './registration/registration.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { ErrorComponent } from './error/error.component';
import { UserDetailsComponent } from './user-page/user-details/user-details.component';
import {MainstatuteComponent} from './main-statute/main-statute.component';
import {BusinessStatuteComponent} from './business-statute/business-statute.component';
import {PrivacyPolicyComponent} from './privacy-policy/privacy-policy.component';
import {CookiePolicyComponent} from './cookie-policy/cookie-policy.component';
import {OneAdvertismentComponent} from './one-advertisment/one-advertisment.component';
import {ContactFormComponent} from './contact-form/contact-form.component';
import { UserDataComponent } from './user-page/user-details/user-data/user-data.component';
import { ModifyProfileComponent } from './user-page/user-details/modify-profile/modify-profile.component';
import {FiltrAdvertismentComponent} from './filtr-advertisment/filtr-advertisment.component';
import { AddAddvertismentComponent } from './user-page/user-details/add-addvertisment/add-addvertisment.component';
import { UserAddvertismentsComponent } from './user-page/user-details/user-addvertisments/user-addvertisments.component';





const routes: Routes = [
  {path: '', component: MainPageComponent, pathMatch: 'full'},
  {path: 'user-page', redirectTo: 'user-page/user-data'},
  {path: 'user-page', component: UserDetailsComponent, children:
  [
    {path: 'user-data', component: UserDataComponent},
    {path: 'user-addvertisments', component: UserAddvertismentsComponent},
    {path: 'buy-premium', component: UserPageComponent},
    {path: 'add-advertisment', component: AddAddvertismentComponent},
    {path: 'edit-profile', component: ModifyProfileComponent}
  ]},
  {path: 'registration', component: RegistrationComponent},
  {path: 'admin', component: AdminPanelComponent},
  {path: 'sign-in', component: SignInComponent},
  {path: 'main-statute', component: MainstatuteComponent},
  {path: 'business-statute', component: BusinessStatuteComponent},
  {path: 'privacy-policy', component: PrivacyPolicyComponent},
  {path: 'cookie-policy', component: CookiePolicyComponent},
  {path: 'one', component: OneAdvertismentComponent},
  {path: 'contact', component: ContactFormComponent},
  {path: 'filtrAdv', component: FiltrAdvertismentComponent},
  {path: 'not-found', component: ErrorComponent, data: {message: 'Nic tutaj nie ma', error: 404, subMessage: 'strona nie znaleziona!'}},
  {path: '**', redirectTo: '/not-found'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
