import {Component, Input, OnInit} from '@angular/core';
import { Event, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss']
})
export class UserPageComponent implements OnInit {
  isModal = false;
  datas = [
    {title: "Pakiet max-premium", limit: "nieograniczone", expires: 90, price: 80},
    {title: "Pakiet medium", limit: 20, expires: 45, price: 35},
    {title: "Pakiet min", limit: 10, expires: 30, price: 15},
  ];

  params;


  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      this.params = params;
    });
  }


}
