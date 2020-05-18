import { Component, OnInit } from '@angular/core';
import { ShortAddvertisment } from '../../interfaces/ShortAddvertisment.model';
import { AddvertismentService } from '../../services/addvertisment/addvertisment.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { QueryParamsAdvertismentSearch } from '../../interfaces/QueryParamsAdvertismentSearch.model';

@Component({
  selector: 'app-show-advertisment',
  templateUrl: './show-advertisment.component.html',
  styleUrls: ['./show-advertisment.component.scss']
})
export class ShowAdvertismentComponent implements OnInit {

  isLoading = false;
  isError = false;
  shortAdvertisments: ShortAddvertisment[];
  totalPage = 1;
  currentPage = 1;
  queryParams: QueryParamsAdvertismentSearch;

  constructor(
    private addvertismentService: AddvertismentService,
    private activatedRoute: ActivatedRoute,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams
    .subscribe((params: Params) => {
      this.currentPage = params.page;
      this.queryParams = params;
      this.isLoading = true;
      this.isError = false;
      this.addvertismentService.getAdvertisements(params)
        .subscribe(([shortAddvertisments, totalPage]) => {
          this.isLoading = false;
          this.isError = false;
          this.totalPage = totalPage;
          if (this.currentPage > this.totalPage || this.currentPage < 1) {
            this.currentPage = 1;
            this.queryParams = {...this.queryParams, page: 1};
            this.router.navigate([], {relativeTo: this.activatedRoute, queryParams: this.queryParams});
          }
          this.shortAdvertisments = shortAddvertisments;
        }, error => {
          this.isLoading = false;
          this.isError = true;
        });
    });
  }

}
