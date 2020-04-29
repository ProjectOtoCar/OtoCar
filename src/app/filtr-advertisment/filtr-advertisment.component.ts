import { Component, OnInit, Input } from '@angular/core';
import { ShortAddvertisment } from '../interfaces/ShortAddvertisment.model';

@Component({
  selector: 'app-filtr-advertisment',
  templateUrl: './filtr-advertisment.component.html',
  styleUrls: ['./filtr-advertisment.component.scss']
})
export class FiltrAdvertismentComponent implements OnInit {
  @Input() shortAdvertisement: ShortAddvertisment;

  constructor() { }

  ngOnInit(): void {
  }

  getUrl(): string {
    return 'url(' + this.shortAdvertisement.images[0]?.photo + ')';
  }
}
