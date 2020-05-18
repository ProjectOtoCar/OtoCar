import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainstatuteComponent } from './main-statute/main-statute.component';
import { BusinessStatuteComponent } from './business-statute/business-statute.component';
import { CookiePolicyComponent } from './cookie-policy/cookie-policy.component';
import { PrivacyPolicyComponent } from './privacy-policy/privacy-policy.component';



@NgModule({
  declarations: [
    MainstatuteComponent,
    BusinessStatuteComponent,
    CookiePolicyComponent,
    PrivacyPolicyComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    MainstatuteComponent,
    BusinessStatuteComponent,
    CookiePolicyComponent,
    PrivacyPolicyComponent
  ]
})
export class StatuteModule { }
