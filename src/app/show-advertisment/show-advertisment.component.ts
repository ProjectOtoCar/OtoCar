import { Component, OnInit } from '@angular/core';
import { ShortAddvertisment } from '../interfaces/ShortAddvertisment.model';
import { AddvertismentService } from '../services/addvertisment/addvertisment.service';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-show-advertisment',
  templateUrl: './show-advertisment.component.html',
  styleUrls: ['./show-advertisment.component.scss']
})
export class ShowAdvertismentComponent implements OnInit {

  isLoading = false;
  isError = false;
  shortAdvertisments: ShortAddvertisment[];
  totalPage = 1;

  constructor(
    private addvertismentService: AddvertismentService,
    private activatedRoute: ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams
    .subscribe((params: Params) => {
      this.addvertismentService.getAdvertisements(params)
        .subscribe(([shortAddvertisments, totalPage]) => {
          this.totalPage = totalPage;
          this.shortAdvertisments = shortAddvertisments;
        });
    });
  }

}
