import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params, Event, Router } from '@angular/router';
import { UserPageService } from 'src/app/services/user-page.service';
import { Seller } from 'src/app/interfaces/Seller';
import { Subscription } from 'rxjs';
import { LoginUserService } from 'src/app/services/loginUser/login-user.service';
import { LoginUser } from 'src/app/interfaces/loginUser.model';

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
  loginUser: LoginUser;
  isDeleteModal: boolean;
  isModalLoading: boolean;
  isErrorModal: boolean;
  queryParamsSub: Subscription;
  loginUserSub: Subscription;
  constructor(
    private activatedRoute: ActivatedRoute,
    private route: Router,
    private userPageService: UserPageService,
    private loginUserService: LoginUserService ) { }
  ngOnInit(): void {
    this.loginUserSub = this.loginUserService.loginUser
      .subscribe((loginUser: LoginUser) => {
        this.loginUser = loginUser;
        this.queryParamsSub = this.activatedRoute.queryParams.subscribe((params: Params) => {
          this.userPageService.isError.next(false);
          this.userPageService.isLoading.next(true);
          if (params.userId) {
            this.userId = params.userId;
          } else {
            if (loginUser) {
              this.userId = loginUser.id;
            }
          }
          this.queryParams = {...this.queryParams, userId: this.userId};
          this.userPageService.downloadUserData(this.userId)
          .subscribe((seller: Seller) => {
            this.userPageService.isError.next(false);
            this.userPageService.isLoading.next(false);
            this.userPageService.isUserFound.next(true);
            if (seller !== null) {
              this.seller = seller;
              this.userPageService.sellerSubject.next(seller);
              this.route.navigate([], {relativeTo: this.activatedRoute, queryParams: {...this.queryParams} , queryParamsHandling: 'merge'});
            }
          }, error => {
            this.userPageService.isError.next(true);
            this.userPageService.isLoading.next(false);
            this.userPageService.isUserFound.next(false);
          }
          );
        });
      });
    this.userPageService.isUserFound.subscribe(
      (isFound: boolean) => {
        this.isFoundUser = isFound;
      }
    );
  }
  onRemoveUser(event: boolean): void {
    this.isErrorModal = false;
    if (event) {
        this.isModalLoading = true;
        this.userPageService.deleteSeller(this.userId).subscribe(() => {
          this.loginUserService.deleteUserById(this.seller.authId)
            .subscribe(() => {
              this.isModalLoading = false;
              this.isDeleteModal = false;
              this.loginUserService.signOut();
              this.route.navigate(['/']);
            }, error => {
              this.isErrorModal = true;
              this.isModalLoading = false;
            });
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
    this.queryParamsSub?.unsubscribe();
    this.loginUserSub?.unsubscribe();
  }

}
