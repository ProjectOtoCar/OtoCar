import { Component, OnInit, OnDestroy, Output, EventEmitter } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { UserPageService } from 'src/app/services/user-page.service';
import { Seller } from 'src/app/interfaces/Seller';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-user-data',
  templateUrl: './user-data.component.html',
  styleUrls: ['./user-data.component.scss']
})
export class UserDataComponent implements OnInit, OnDestroy {
  isError: boolean;
  isLoading: boolean;
  userId: number;
  seller: Seller;
  queryParamsSub: Subscription;

  constructor(
    private route: Router,
    private activatedRoute: ActivatedRoute,
    private userPageService: UserPageService
  ) {
    this.isError = false;
    this.isLoading = false;
   }

  ngOnInit(): void {
    this.queryParamsSub = this.activatedRoute.queryParams.subscribe((params: Params) => {
      this.isLoading = true;
      this.isError = false;
      if (params.userId) {
        this.userId = params.userId;
      } else {
        this.userId = 2; // zmienic na id usera zalogowanego
      }
      this.userPageService.downloadUserData(this.userId)
      .subscribe((seller: Seller) => {
        this.isError = false;
        this.isLoading = false;
        this.userPageService.isUserFound.next(true);
        if (seller !== null) {
          this.seller = seller;
        } // dodać przekierowanie na zalogowanego usera
      }, error => {
        this.isError = true;
        this.isLoading = false;
        this.userPageService.isUserFound.next(false);
      }
      );
    });
  }
  ngOnDestroy(): void {
    this.queryParamsSub.unsubscribe();
  }
  random(): number {
    return Math.random();
  }

}
