import {Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent implements OnInit {
  isModal = false;
  isSuccessModal = false;
  @Input()
  data;

  constructor() {
  }

  ngOnInit(): void {
  }

  showModal(): void {
    this.isModal = true;
  }

  onAction(event: boolean): void {
    if (event) {
      this.isModal = false;
      this.isSuccessModal = true;
    } else {
      this.isModal = false;
    }
  }

  closeSuccessModal(): void {
    this.isSuccessModal = false;
  }
}
