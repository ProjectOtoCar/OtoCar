import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserPageComponent } from './user-page/user-page.component';
import { MainPageComponent } from './main-page/main-page.component';



const routes: Routes = [
  {path: '', component: MainPageComponent, pathMatch: 'full'},
  {path: 'userPage', component: UserPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
