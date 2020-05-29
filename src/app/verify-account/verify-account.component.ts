import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ActiveAccountService } from '../services/activeAccount/active-account.service';

@Component({
  selector: 'app-verify-account',
  templateUrl: './verify-account.component.html',
  styleUrls: ['./verify-account.component.scss']
})
export class VerifyAccountComponent implements OnInit {

  isActive = false;
  isLoading = false;
  isError = false;
  constructor(
    private activatedRoute: ActivatedRoute,
    private activeAccountService: ActiveAccountService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams
      .subscribe((params: Params) => {
        this.isLoading = true;
        console.log('a');
        this.isActive = false;
        this.isError = false;
        if (params.token) {
          this.activeAccountService
            .activeAccount(params.token)
            .subscribe(() => {
              this.isActive = true;
              this.isLoading = false;
              this.isError = false;
            }, error => {
              this.isError = true;
              console.log('v');
              this.isLoading = false;
            });
        } else {
          this.router.navigate(['/']);
          this.isError = true;
          this.isLoading = false;
        }
      }, error => {
        this.isError = true;
        console.log('ccv');
        this.isLoading = false;
      });
  }
  onActiveAccount(): void {
    this.router.navigate(['/sign-in']);
  }
}
