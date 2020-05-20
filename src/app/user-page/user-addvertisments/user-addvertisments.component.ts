import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { AddvertismentService } from 'src/app/services/addvertisment/addvertisment.service';
import { ShortAddvertisment } from 'src/app/interfaces/ShortAddvertisment.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-user-addvertisments',
  templateUrl: './user-addvertisments.component.html',
  styleUrls: ['./user-addvertisments.component.scss']
})
export class UserAddvertismentsComponent implements OnInit, OnDestroy {
  isLoading = false;
  isError = false;
  page = 1;
  maxPage: number;
  sellerId: number;
  queryParams = {};
  shortAddvertisments: ShortAddvertisment[];
  paramsSub: Subscription;
  constructor(
    private addvertismentService: AddvertismentService,
    private route: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.paramsSub = this.activatedRoute.queryParams
    .subscribe((params: Params) => {
      this.isLoading = true;
      this.isError = false;
      this.sellerId = params.userId;
      this.page = params.page || 1;
      this.queryParams = {userId: this.sellerId, page: this.page};
      this.addvertismentService
      .getAddvertismentBySeller(this.sellerId, this.page)
      .subscribe(([shortAddvertisments, totalPages]) => {
        this.isLoading = false;
        this.isError = false;
        this.maxPage = totalPages;
        this.shortAddvertisments = shortAddvertisments;
        if (+this.maxPage < Number(this.page)
        || this.page < 1) {
          this.route.navigate([],
            {
              relativeTo: this.activatedRoute,
              queryParams: {...this.queryParams, page: 1}
            });
        }
      }, error => {
        this.isLoading = false;
        this.isError = true;
      });
    });
  }

  deleteItem(id: number): void {
    this.shortAddvertisments = this.shortAddvertisments
    .filter((shortAddvertisment: ShortAddvertisment) => shortAddvertisment.id !== id);
    if (this.shortAddvertisments.length === 0) {
      this.route.navigate([],
        {
          relativeTo: this.activatedRoute,
          queryParams: {...this.queryParams, afc: Math.random()}
        });
    }
  }

  random(): number {
    return Math.random();
  }

  ngOnDestroy(): void {
   this.paramsSub?.unsubscribe();
  }
}
