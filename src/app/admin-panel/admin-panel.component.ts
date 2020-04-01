import {Component, Input, OnInit, OnDestroy} from '@angular/core';
import { AdminPanelService } from '../services/admin-panel.service';
import { Seller } from '../interfaces/Seller';
import { pipe, Subscription, forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent implements OnInit, OnDestroy {

  datas: Seller[];
  currentData: number;
  isLoading = true;
  getSubscription: Subscription;
  page: number;
  @Input()
  data;

  constructor(
    private adminPanelService: AdminPanelService,
    private activedRoute: ActivatedRoute) {
      this.page = 1;
    }

  ngOnInit(): void {
    this.isLoading = true;
    this.activedRoute.queryParams
    .subscribe((params: Params) => {
      this.page = params.page || 1;
      this.getSubscription = this.adminPanelService.getSellers(this.page).subscribe(response => {
        this.datas = response;
        this.currentData = Date.now();
        this.isLoading = false;
      });
    });
  }
  deleteUser(id: number) {
    this.adminPanelService.deleteSeller(id)
    .subscribe(response => {
      this.datas = this.datas.filter((data: Seller) => data.id !== id);
    });
  }

  ngOnDestroy(): void {
   this.getSubscription.unsubscribe();
  }

}
