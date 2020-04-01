import { Component, OnInit, Input } from '@angular/core';
import { Page } from 'src/app/interfaces/Page';

@Component({
  selector: 'app-pagination-item',
  templateUrl: './pagination-item.component.html',
  styleUrls: ['./pagination-item.component.scss']
})
export class PaginationItemComponent implements OnInit {
  @Input() page: Page;
  constructor() { }

  ngOnInit(): void {
    console.log(this.page);
  }

}
