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
export class UserDataComponent implements OnInit {
  isError: boolean;
  isLoading: boolean;
  userId: number;
  seller: Seller;
  constructor(
    private route: Router,
    private activatedRoute: ActivatedRoute,
    private userPageService: UserPageService
  ) {
    this.isError = false;
    this.isLoading = false;
   }

  ngOnInit(): void {
    this.userPageService.isError.subscribe((isError: boolean) => this.isError = isError);
    this.userPageService.isLoading.subscribe((isLoading: boolean) => this.isLoading = isLoading);
    this.userPageService.sellerSubject.subscribe((seller: Seller) => this.seller = seller);
  }
  random(): number {
    return Math.random();
  }

}
