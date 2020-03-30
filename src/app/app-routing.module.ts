import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserPageComponent } from './user-page/user-page.component';
import { MainPageComponent } from './main-page/main-page.component';
import { RegistrationComponent } from './registration/registration.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { ErrorComponent } from './error/error.component';
import { UserDetailsComponent } from './user-page/user-details/user-details.component';



const routes: Routes = [
  {path: '', component: MainPageComponent, pathMatch: 'full'},
  {path: 'user-page', component: UserDetailsComponent, children:
  [
    {path: 'buy-premium', component: UserPageComponent}
  ]},
  {path: 'registration', component: RegistrationComponent},
  {path: 'admin', component: AdminPanelComponent},
  {path: 'sign-in', component: SignInComponent},
  {path: 'not-found', component: ErrorComponent, data: {message: 'Nic tutaj nie ma', error: 404, subMessage: 'strona nie znaleziona!'}},
  {path: '**', redirectTo: '/not-found'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
