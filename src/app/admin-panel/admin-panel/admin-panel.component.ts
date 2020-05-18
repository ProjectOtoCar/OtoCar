import {Component, Input, OnInit, OnDestroy} from '@angular/core';
import { AdminPanelService } from '../../services/admin-panel.service';
import { Seller } from '../../interfaces/Seller';
import { pipe, Subscription, forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { QueryParamsAdminPage } from '../../interfaces/QueryParamsAdminPage';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent implements OnInit, OnDestroy {
  searchForm: FormGroup;
  datas: Seller[];
  currentData: number;
  isLoading = true;
  isDeleteSellerLoading = false;
  isDeleteSellerError = false;
  isError = false;
  getSubscription: Subscription;
  page: number;
  maxPage: number;
  queryParams: QueryParamsAdminPage;
  @Input()
  data;

  constructor(
    private adminPanelService: AdminPanelService,
    private activatedRoute: ActivatedRoute,
    private route: Router) {
      this.page = 1;
    }

  ngOnInit(): void {
    this.loadData();
  }
  deleteUser(id: number) {
    this.isDeleteSellerLoading = true;
    this.isDeleteSellerError = false;
    this.adminPanelService.deleteSeller(id)
    .subscribe(response => {
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
  }
  createQueryParams(queryParams: QueryParamsAdminPage) {
    this.queryParams = {...queryParams, page: this.page || 1};
    this.route.navigate([],
       {
         relativeTo: this.activatedRoute,
         queryParams: this.queryParams
       });
  }
  ngOnDestroy(): void {
   this.getSubscription.unsubscribe();
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
}
