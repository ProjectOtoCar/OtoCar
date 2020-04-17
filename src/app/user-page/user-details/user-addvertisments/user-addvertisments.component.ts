import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { AddvertismentService } from 'src/app/services/addvertisment/addvertisment.service';
import { ShortAddvertisment } from 'src/app/interfaces/ShortAddvertisment.model';

@Component({
  selector: 'app-user-addvertisments',
  templateUrl: './user-addvertisments.component.html',
  styleUrls: ['./user-addvertisments.component.scss']
})
export class UserAddvertismentsComponent implements OnInit {
  isLoading = false;
  isError = false;
  page = 1;
  maxPage: number;
  sellerId: number;
  queryParams = {};
  shortAddvertisments: ShortAddvertisment[];
  constructor(
    private addvertismentService: AddvertismentService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams
    .subscribe((params: Params) => {
      this.isLoading = true;
      this.isError = false;
      if (params.page !== undefined) {
        this.page = params.page;
      }
      this.sellerId = params.userId;
      this.queryParams = {userId: this.sellerId, page: this.page};
      this.addvertismentService
      .getAddvertismentBySeller(this.sellerId, this.page)
      .subscribe(([shortAddvertisments, totalPages]) => {
        this.isLoading = false;
        this.isError = false;
        this.maxPage = totalPages;
        this.shortAddvertisments = shortAddvertisments;
      }, error => {
        this.isLoading = false;
        this.isError = true;
      });
    });
  }
  random(): number {
    return Math.random();
  }
}
