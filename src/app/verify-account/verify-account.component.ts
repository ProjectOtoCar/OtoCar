import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
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
    private activeAccountService: ActiveAccountService
  ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams
      .subscribe((params: Params) => {
        if (params.token) {
          this.activeAccountService
            .activeAccout(params.token)
            .subscribe(() => {
              this.isActive = true;
            }, error => {

            });
        }
      });
  }

}
