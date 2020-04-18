import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-error-message',
  templateUrl: './error-message.component.html',
  styleUrls: ['./error-message.component.scss']
})
export class ErrorMessageComponent implements OnInit {
  @Input() errorMessage = 'Coś poszło nie tak.';
  @Input() isLink = false;
  @Input() url: string[];
  @Input() queryParams;
  @Input() linkMessage;
  constructor() { }

  ngOnInit(): void {
    this.queryParams = {...this.queryParams, afc: Math.random()};
  }

}
