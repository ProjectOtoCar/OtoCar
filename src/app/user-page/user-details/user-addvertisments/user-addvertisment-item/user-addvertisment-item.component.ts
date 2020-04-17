import { Component, OnInit, Input } from '@angular/core';
import { ShortAddvertisment } from 'src/app/interfaces/ShortAddvertisment.model';

@Component({
  selector: 'app-user-addvertisment-item',
  templateUrl: './user-addvertisment-item.component.html',
  styleUrls: ['./user-addvertisment-item.component.scss']
})
export class UserAddvertismentItemComponent implements OnInit {

  @Input() shortAdvertisment: ShortAddvertisment;

  constructor() { }

  ngOnInit(): void {
    console.log(this.shortAdvertisment.images[0]?.photo);
  }

  showDetails(): void {

  }

  modifyAddvertisment(): void {

  }

  deleteAddvertisment(): void {

  }

  toggleActive(): void {

  }
  getUrl(): string {
    return 'url(' + this.shortAdvertisment.images[0]?.photo + ')';
  }

}
