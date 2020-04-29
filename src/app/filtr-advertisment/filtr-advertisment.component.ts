import { Component, OnInit, Input } from '@angular/core';
import { ShortAddvertisment } from '../interfaces/ShortAddvertisment.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-filtr-advertisment',
  templateUrl: './filtr-advertisment.component.html',
  styleUrls: ['./filtr-advertisment.component.scss']
})
export class FiltrAdvertismentComponent implements OnInit {
  @Input() shortAdvertisement: ShortAddvertisment;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  getUrl(): string {
    return 'url(' + this.shortAdvertisement.images[0]?.photo + ')';
  }

  showDetails(): void {
    this.router.navigate(['advertisement'], {queryParams: {id: this.shortAdvertisement.id}});
  }
}
