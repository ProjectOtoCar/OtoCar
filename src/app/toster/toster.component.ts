import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-toster',
  templateUrl: './toster.component.html',
  styleUrls: ['./toster.component.scss']
})
export class TosterComponent implements OnInit {
  isShow = true;
  constructor() { }

  ngOnInit(): void {
    setTimeout(() => {
      this.isShow = false;
    }, 1000000);
  }
  onClose(): void {
    this.isShow = false;
  }
}
