import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss']
})
export class UserPageComponent implements OnInit {


  datas = [
    {title: "Pakiet min", limit: 10, expires: 30, price: 15},
    {title: "Pakiet medium", limit: 20, expires: 45, price: 35},
    {title: "Pakiet max-premium", limit: "nieograniczone", expires: 90, price: 80}
  ];

  @Input()
  data;


  constructor() {
  }

  ngOnInit(): void {
  }

}
