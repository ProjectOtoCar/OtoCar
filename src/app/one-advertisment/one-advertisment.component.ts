import {Component, OnInit} from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-one-advertisment',
  templateUrl: './one-advertisment.component.html',
  styleUrls: ['./one-advertisment.component.scss']
})
export class OneAdvertismentComponent implements OnInit{

  constructor(config: NgbCarouselConfig) {
    config.wrap = true;
    config.keyboard = false;
    //config.pauseOnHover = false;
  }
  ngOnInit() {
  }
}
