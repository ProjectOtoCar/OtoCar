import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent implements OnInit {

  datas = [
    {id: 1, name: "Janusz",username:"Janek", email: "jan@janusz.pl", premium: 2, premiumExpire: 20, registerData: "25.03.2020"},
    {id: 2, name: "Janusz",username:"Janek", email: "jan@janusz.pl", premium: 2, premiumExpire: 20, registerData: "25.03.2020"},
    {id: 3, name: "Janusz",username:"Janek", email: "jan@janusz.pl", premium: 2, premiumExpire: 20, registerData: "25.03.2020"},
  ];

  @Input()
  data;

  constructor() { }

  ngOnInit(): void {
  }

}
