import {Component, Input, OnInit, OnDestroy} from '@angular/core';
import { AdminPanelService } from '../../services/admin-panel.service';
import { Seller } from '../../interfaces/Seller';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { QueryParamsAdminPage } from '../../interfaces/QueryParamsAdminPage';
import { LoginUserService } from 'src/app/services/loginUser/login-user.service';
import { SerachAdminFormService } from 'src/app/services/serach-admin-form.service';
import { SellerService } from 'src/app/services/seller/seller.service';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent implements OnInit, OnDestroy {
  searchForm: FormGroup;
  datas: Seller[];
  accountsTypes: string[];
  currentData: number;
  isLoading = true;
  isDeleteSellerLoading = false;
  isDeleteSellerError = false;
  isError = false;
  isTypeChangeLoading = false;
  isTypeChangeError = false;
  getSubscription: Subscription;
  page: number;
  maxPage: number;
  queryParams: QueryParamsAdminPage;
  @Input()
  data;

  constructor(
    private adminPanelService: AdminPanelService,
    private searchAdminFormService: SerachAdminFormService,
    private loginUserService: LoginUserService,
    private sellerService: SellerService,
    private activatedRoute: ActivatedRoute,
    private route: Router) {
      this.page = 1;
    }

  ngOnInit(): void {
    this.loadData();
    this.searchAdminFormService.getAccountTypes().subscribe((data: [string]) => {
      this.accountsTypes = data;
      this.isLoading = false;
    }, error => {
    this.isLoading = false;
    this.isError = true;
    });
  }
  deleteUser(id: number) {
    this.isDeleteSellerLoading = true;
    this.isDeleteSellerError = false;
    this.adminPanelService.deleteSeller(id)
    .subscribe(response => {
      this.loginUserService
      .deleteUserById(this.datas.filter((data: Seller) => data.id === id)[0].authId)
      .subscribe(() => {
        this.isDeleteSellerLoading = false;
        this.datas = this.datas.filter((data: Seller) => data.id !== id);
        if (this.datas.length === 0) {
          this.route.navigate([],
            {
              relativeTo: this.activatedRoute,
              queryParams: this.queryParams
            });
        }
      }, error => {
        this.isDeleteSellerLoading = false;
        this.isDeleteSellerError = true;
      });
    }, error => {
      this.isDeleteSellerLoading = false;
      this.isDeleteSellerError = true;
    });
  }
  createQueryParams(queryParams: QueryParamsAdminPage) {
    this.queryParams = {...queryParams, page: this.page || 1};
    this.route.navigate([],
       {
         relativeTo: this.activatedRoute,
         queryParams: this.queryParams
       });
  }

  random(): number {
    return Math.random();
  }
  closeErrorModal(): void {
    this.isDeleteSellerError = false;
  }
  private loadData(): void {
    this.activatedRoute.queryParams
    .subscribe((params: Params) => {
      this.page = params.page || 1;
      this.isLoading = true;
      this.isError = false;
      this.getSubscription = this.adminPanelService.getSellers
      (
        this.page,
        this.queryParams?.firstName,
        this.queryParams?.lastName,
        this.queryParams?.type,
        this.queryParams?.premium,
        this.queryParams?.sort
      )
        .subscribe(([response, maxPage]) => {
        this.datas = response;
        this.maxPage = maxPage;
        console.log(this.datas);
        if (+this.maxPage < +this.page
            || this.page < 1 ) {
          this.route.navigate([],
            {
              relativeTo: this.activatedRoute,
              queryParams: {...this.queryParams, page: 1}
            });
        }
        this.currentData = Date.now();
        this.isLoading = false;
      },
      error => {
        this.isError = true;
        this.isLoading = false;
      });
    });
  }
  onTypeChange(type: string, authId: number, id: number): void {
    this.isTypeChangeLoading = true;
    this.isTypeChangeError = false;
    console.log('l');
    this.sellerService
      .patchSeller(id, { type })
      .subscribe(() => {
        this.loginUserService
        .patchAppUSer(authId, {role: 'ROLE_' + type})
        .subscribe(() => {
          this.isTypeChangeLoading = false;
        }, error => {
          this.isTypeChangeLoading = false;
          this.isTypeChangeError = true;
        });
      }, error => {
        this.isTypeChangeLoading = false;
        this.isTypeChangeError = true;
      });

  }
  ngOnDestroy(): void {
    this.getSubscription.unsubscribe();
   }
}
