import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params, Event, Router } from '@angular/router';
import { UserPageService } from 'src/app/services/user-page.service';
import { Seller } from 'src/app/interfaces/Seller';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit, OnDestroy {
  queryParams;
  isFoundUser: boolean;
  isError: boolean;
  isLoading: boolean;
  userId: number;
  seller: Seller;
  isDeleteModal: boolean;
  isModalLoading: boolean;
  isErrorModal: boolean;
  queryParamsSub: Subscription;
  constructor(
    private activatedRoute: ActivatedRoute,
    private route: Router,
    private userPageService: UserPageService ) { }
  ngOnInit(): void {
    this.queryParamsSub = this.activatedRoute.queryParams.subscribe((params: Params) => {
      this.userPageService.isError.next(false);
      this.userPageService.isLoading.next(true);
      if (params.userId) {
        this.userId = params.userId;
      } else {
        this.userId = 2; // zmienic na id usera zalogowanego
      }
      this.queryParams = {...this.queryParams, userId: this.userId};
      this.route.navigate([], {relativeTo: this.activatedRoute, queryParams: {...this.queryParams}});
      this.userPageService.downloadUserData(this.userId)
      .subscribe((seller: Seller) => {
        this.userPageService.isError.next(false);
        this.userPageService.isLoading.next(false);
        this.userPageService.isUserFound.next(true);
        if (seller !== null) {
          this.seller = seller;
          this.userPageService.sellerSubject.next(seller);
        } // dodać przekierowanie na zalogowanego usera
      }, error => {
        this.userPageService.isError.next(true);
        this.userPageService.isLoading.next(false);
        this.userPageService.isUserFound.next(false);
      }
      );
    });
    this.userPageService.isUserFound.subscribe(
      (isFound: boolean) => {
        this.isFoundUser = isFound;
      }
    );
  }
  onRemoteUser(event: boolean): void {
    this.isErrorModal = false;
    if (event) {
        this.isModalLoading = true;
        this.userPageService.deleteSeller(this.userId).subscribe(() => {
        this.isModalLoading = false;
        this.isDeleteModal = false;
      }, error => {
        this.isErrorModal = true;
        this.isModalLoading = false;
      }
      );
    } else {
      this.isDeleteModal = false;
    }
  }
  onShowDeleteModal(): void {
    this.isDeleteModal = true;
  }
  ngOnDestroy(): void {
    this.queryParamsSub.unsubscribe();
  }

}
