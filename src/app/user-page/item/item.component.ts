import {Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent implements OnInit {
  datas = {
    title : ["Pakiet min", "Pakiet medium", "Pakiet max-premium"],
    lmiit : [10, 20, "nieograniczone"],
    expires : [30, 45, 90],
    price : [15, 35, 80]
  }

  @Input()
  data;

  constructor() {
  }

  ngOnInit(): void {
  }

}
