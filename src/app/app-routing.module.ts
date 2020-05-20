import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainPageComponent } from './main-page/main-page.component';
import { RegistrationComponent } from './registration/registration/registration.component';
import { AdminPanelComponent } from './admin-panel/admin-panel/admin-panel.component';
import { SignInComponent } from './sign-in/sign-in/sign-in.component';
import { ErrorComponent } from './error/error.component';
import {MainstatuteComponent} from './status/main-statute/main-statute.component';
import {BusinessStatuteComponent} from './status/business-statute/business-statute.component';
import {PrivacyPolicyComponent} from './status/privacy-policy/privacy-policy.component';
import {CookiePolicyComponent} from './status/cookie-policy/cookie-policy.component';
import {OneAdvertismentComponent} from './one-advertisment/one-advertisment/one-advertisment.component';
import {ContactFormComponent} from './contact-form/contact-form/contact-form.component';
import { AdvertismentModifyComponent } from './advertisment-modify/advertisment-modify/advertisment-modify.component';
import { ShowAdvertismentComponent } from './show-advertisment/show-advertisment/show-advertisment.component';
import { AuthAdminGuard } from './guard/authAdmin.guard';
import { AuthNotLoginGuard } from './guard/authNotLogin.guard';
import { AuthGuard } from './guard/auth.guard';
import { UserDetailsComponent } from './user-page/user-details/user-details.component';
import { UserDataComponent } from './user-page/user-data/user-data.component';
import { UserAddvertismentsComponent } from './user-page/user-addvertisments/user-addvertisments.component';
import { UserPageComponent } from './user-page/user-page/user-page.component';
import { AddAddvertismentComponent } from './user-page/add-addvertisment/add-addvertisment.component';
import { ModifyProfileComponent } from './user-page/modify-profile/modify-profile.component';
import { AuthUserGuard } from './guard/authUser.guard';
import { AuthOwnerGuard } from './guard/authOwner.guard';





const routes: Routes = [
  {path: '', component: MainPageComponent, pathMatch: 'full'},
  {path: 'user-page', redirectTo: 'user-page/user-data'},
  {path: 'user-page', component: UserDetailsComponent, children:
  [
    {path: 'user-data', component: UserDataComponent},
    {path: 'user-advertisements', component: UserAddvertismentsComponent},
    {path: 'buy-premium', component: UserPageComponent, canActivate: [AuthOwnerGuard]},
    {path: 'add-advertisement', component: AddAddvertismentComponent, canActivate: [AuthUserGuard]},
    {path: 'edit-profile', component: ModifyProfileComponent, canActivate: [AuthUserGuard]}
  ]},
  {path: 'registration', component: RegistrationComponent, canActivate: [AuthNotLoginGuard]},
  {path: 'admin', component: AdminPanelComponent, canActivate: [AuthAdminGuard]},
  {path: 'sign-in', component: SignInComponent, canActivate: [AuthNotLoginGuard]},
  {path: 'main-statute', component: MainstatuteComponent},
  {path: 'business-statute', component: BusinessStatuteComponent},
  {path: 'privacy-policy', component: PrivacyPolicyComponent},
  {path: 'cookie-policy', component: CookiePolicyComponent},
  {path: 'advertisement', component: OneAdvertismentComponent},
  {path: 'advertisements', component: ShowAdvertismentComponent},
  {path: 'advertisement-modify', component: AdvertismentModifyComponent, canActivate: [AuthGuard]},
  {path: 'contact', component: ContactFormComponent},
  {path: 'not-found', component: ErrorComponent, data: {message: 'Nic tutaj nie ma', error: 404, subMessage: 'strona nie znaleziona!'}},
  {path: '**', redirectTo: '/not-found'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
