import {Component, OnInit} from '@angular/core';
import { QueryParamsAdvertismentSearch } from '../interfaces/QueryParamsAdvertismentSearch.model';
import { Router } from '@angular/router';


@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {
  constructor(private route: Router) { }
  ngOnInit(): void {}

  onGetQueryParams(event: QueryParamsAdvertismentSearch): void {
    this.route.navigate(['advertisements'], {queryParams: event});
  }
}
