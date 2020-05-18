import { Component, OnInit, Input, OnChanges, SimpleChanges} from '@angular/core';
import { Page } from '../../interfaces/Page';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnInit, OnChanges {
  @Input() url: string;
  @Input() maxPage: number;
  @Input() currentPage: number;
  @Input() queryParams: object;
  pages: Page[];
  constructor() {
  }
  ngOnChanges(changes: SimpleChanges): void {
   this.updatePages();
  }
  ngOnInit(): void {
    this.updatePages();
  }
  private updatePages(): void {
    if (this.maxPage >= 3) {
      this.threeAndMorePage();
    } else {
      this.twoPages();
    }
  }
  private twoPages(): void {
    this.pages =
    [
      {
        url: this.url,
        maxPage: this.maxPage,
        page: 'poprzednia',
        queryParams: {
          ...this.queryParams,
          page: this.currentPage <= 1 ? this.currentPage : this.currentPage - 1,
        },
        disabled: this.currentPage <= 1,
      },
      {
        url: this.url,
        maxPage: this.maxPage,
        page: this.currentPage <= 1 ? this.currentPage : this.currentPage - 1,
        queryParams: {
          ...this.queryParams,
          page: this.currentPage <= 1 ? this.currentPage : this.currentPage - 1,
        },
        active: this.currentPage <= 1,
      },
      {
        url: this.url,
        maxPage: this.maxPage,
        page: this.currentPage < this.maxPage ?
        +this.currentPage + 1 : this.currentPage,
        queryParams: {
          ...this.queryParams,
          page: this.currentPage < this.maxPage ?
          +this.currentPage + 1 : this.currentPage,
        },
        active: this.currentPage >= this.maxPage
      },
      {
        url: this.url,
        maxPage: this.maxPage,
        page: 'nastepna',
        queryParams: {
          ...this.queryParams,
          page: this.currentPage < this.maxPage ?
          +this.currentPage + 1 : this.currentPage,
        },
        disabled: +this.currentPage === +this.maxPage,
      },
    ];
  }
  private threeAndMorePage(): void {
    this.pages =
    [
      {
        url: this.url,
        maxPage: this.maxPage,
        page: 'poprzednia',
        queryParams: {
          ...this.queryParams,
          page: this.currentPage <= 1 ? this.currentPage : this.currentPage - 1,
        },
        disabled: +this.currentPage === 1,
      },
      {
        url: this.url,
        maxPage: this.maxPage,
        page: this.currentPage <= 1 ?
        this.currentPage : +this.currentPage === +this.maxPage ?
        this.currentPage - 2 :
        this.currentPage - 1,
        queryParams: {
          ...this.queryParams,
          page: this.currentPage <= 1 ?
          this.currentPage : +this.currentPage === +this.maxPage ?
          this.currentPage - 2 :
          this.currentPage - 1,
        },
        active: this.currentPage <= 1,
      },
      {
        url: this.url,
        maxPage: this.maxPage,
        page: this.currentPage <= 1 ?
        +this.currentPage + 1 : +this.maxPage === +this.currentPage ?
         this.currentPage - 1 : +this.currentPage,
        queryParams: {
          ...this.queryParams,
          page: this.currentPage <= 1 ?
          +this.currentPage + 1 : +this.maxPage === +this.currentPage ?
          this.currentPage - 1 : +this.currentPage,
        },
        active: this.currentPage > 1 && this.currentPage < this.maxPage,
      },
      {
        url: this.url,
        maxPage: this.maxPage,
        page: this.currentPage < +this.maxPage ?
        this.currentPage <= 1 ?
        +this.currentPage + 2 : +this.currentPage + 1 : +this.currentPage,
        queryParams: {
          ...this.queryParams,
          page: this.currentPage < +this.maxPage ?
          this.currentPage <= 1 ? +this.currentPage + 2 : +this.currentPage + 1 : +this.currentPage,
        },
        active: this.currentPage >= this.maxPage
      },
      {
        url: this.url,
        maxPage: this.maxPage,
        page: 'nastepna',
        queryParams: {
          ...this.queryParams,
          page: this.currentPage < this.maxPage ?
          +this.currentPage + 1 : this.currentPage,
        },
        disabled: +this.currentPage === +this.maxPage,
      },
    ];
  }

}
