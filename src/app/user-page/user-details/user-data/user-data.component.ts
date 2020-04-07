import { Component, OnInit, OnDestroy } from '@angular/core';
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
  userId: number;
  seller: Seller;
  queryParamsSub: Subscription;

  constructor(
    private route: Router,
    private activatedRoute: ActivatedRoute,
    private userPageService: UserPageService
  ) { }

  ngOnInit(): void {
    this.queryParamsSub = this.activatedRoute.queryParams.subscribe((params: Params) => {
      if (params.userId) {
        this.userId = params.userId;
      } else {
        this.userId = 1; // zmienic na id usera zalogowanego
      }
      this.userPageService.downloadUserData(this.userId)
      .subscribe((seller: Seller) => {
        if (seller !== null) {
          this.seller = seller;
        } // dodać przekierowanie na zalogowanego usera
      }
      );
    });
  }
  ngOnDestroy(): void {
    this.queryParamsSub.unsubscribe();
  }


}
