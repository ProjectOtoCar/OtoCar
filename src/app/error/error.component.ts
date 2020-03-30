import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Data } from '@angular/router';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.scss']
})
export class ErrorComponent implements OnInit {
  errorMessage: string;
  errorStatus: number;
  subMessage: string;
  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.errorMessage = this.activatedRoute.snapshot.data.message;
    this.activatedRoute.data.subscribe(
      (data: Data) => {
          this.errorMessage = data.message;
          this.errorStatus = data.error;
          this.subMessage = data.subMessage;
      }
    );
  }

}
