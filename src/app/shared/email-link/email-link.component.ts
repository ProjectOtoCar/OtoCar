import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-email-link',
  templateUrl: './email-link.component.html',
  styleUrls: ['./email-link.component.scss']
})
export class EmailLinkComponent implements OnInit {
  @Input() email: string;
  constructor() { }

  ngOnInit(): void {
  }

}
