import {Component, OnInit, OnDestroy} from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Params } from '@angular/router';
import { AddvertismentService } from '../../services/addvertisment/addvertisment.service';
import { Subscription } from 'rxjs';
import { Addvertisment } from '../../interfaces/Addvertisment';

@Component({
  selector: 'app-one-advertisment',
  templateUrl: './one-advertisment.component.html',
  styleUrls: ['./one-advertisment.component.scss']
})
export class OneAdvertismentComponent implements OnInit, OnDestroy {
  isLoading = false;
  isError = false;
  isShowImageModal = false;
  isShowContactModal = false;
  currentPhoto;
  addvertisment: Addvertisment;

  paramsSub: Subscription;
  constructor(
    private config: NgbCarouselConfig,
    private addvertismentService: AddvertismentService,
    private activatedRoute: ActivatedRoute
    ) {
    config.wrap = true;
    config.keyboard = false;
    // config.pauseOnHover = false;
  }

  ngOnInit() {
    this.paramsSub = this.activatedRoute
    .queryParams
    .subscribe((params: Params) => {
      this.isLoading = true;
      this.isError = false;
      if (params.id === undefined) {
        this.isLoading = false;
        this.isError = true;
        return;
      }
      this.addvertismentService.getAdvertisement(params.id)
      .subscribe((addvertisment: Addvertisment) => {
        this.isLoading = false;
        this.isError = false;
        this.addvertisment = addvertisment;
        console.log(this.addvertisment);
      }, error => {
        this.isLoading = false;
        this.isError = true;
      });
    }, error => {
      this.isLoading = false;
      this.isError = true;
    });
  }

  showImage(photo): void {
    this.isShowImageModal = true;
    this.currentPhoto = photo;
  }
  showContactModal(): void {
    this.isShowContactModal = true;
  }

  closeImageModal(): void {
    this.isShowImageModal = false;
  }
  closeContactModal(): void {
    this.isShowContactModal = false;
  }

  ngOnDestroy(): void {
    this.paramsSub.unsubscribe();
  }
}
