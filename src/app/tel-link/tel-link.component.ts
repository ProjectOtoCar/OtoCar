import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-tel-link',
  templateUrl: './tel-link.component.html',
  styleUrls: ['./tel-link.component.scss']
})
export class TelLinkComponent implements OnInit {
  @Input() phoneNumber: string;
  constructor() { }

  ngOnInit(): void {
  }

}
