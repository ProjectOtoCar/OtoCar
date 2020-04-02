import {Component, Input, OnInit, OnDestroy} from '@angular/core';
import { AdminPanelService } from '../services/admin-panel.service';
import { Seller } from '../interfaces/Seller';
import { pipe, Subscription, forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { QueryParamsAdminPage } from '../interfaces/QueryParamsAdminPage';

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
  getSubscription: Subscription;
  page: number;
  maxPage: number;
  queryParams: QueryParamsAdminPage;
  @Input()
  data;

  constructor(
    private adminPanelService: AdminPanelService,
    private activedRoute: ActivatedRoute,
    private route: Router) {
      this.page = 1;
    }

  ngOnInit(): void {
    this.loadData();
  }
  deleteUser(id: number) {
    this.adminPanelService.deleteSeller(id)
    .subscribe(response => {
      this.datas = this.datas.filter((data: Seller) => data.id !== id);
    });
  }
  createQueryParams(queryParams: QueryParamsAdminPage) {
    this.queryParams = {...queryParams, page: this.page || 1};
    this.route.navigate([],
       {
         relativeTo: this.activedRoute,
         queryParams: this.queryParams
       });
  }
  ngOnDestroy(): void {
   this.getSubscription.unsubscribe();
  }
  private loadData(): void {
    this.isLoading = true;
    this.activedRoute.queryParams
    .subscribe((params: Params) => {
      this.page = params.page || 1;
      this.getSubscription = this.adminPanelService.getSellers(
        this.page,
        this.queryParams?.firstName,
        this.queryParams?.lastName,
        this.queryParams?.type,
        this.queryParams?.premium,
        this.queryParams?.sort)
        .subscribe(([response, maxPage]) => {
        this.datas = response;
        this.maxPage = maxPage;
        this.currentData = Date.now();
        this.isLoading = false;
      });
    });
  }
}
