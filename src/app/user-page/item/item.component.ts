import {Component, OnInit, Input} from '@angular/core';
import { UserPageService } from 'src/app/services/user-page.service';
import { PremiumData } from 'src/app/interfaces/premiumData';
import { HttpParams } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent implements OnInit {
  isModal = false;
  isLoading = false;
  isSuccessModal = false;
  isFailModal = false;
  @Input()
  data: PremiumData;
  @Input() params;

  constructor(
    private userPageService: UserPageService,
    private route: Router,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
  }

  showModal(): void {
    this.isModal = true;
  }

  onAction(event: boolean): void {
    if (event) {
      this.isLoading = true;
      this.userPageService.buyPremium(this.params.userId, this.data.expires).subscribe(() => {
        this.isModal = false;
        this.isSuccessModal = true;
        this.isLoading = false;
      }
      , (error) => {
        console.log(error);
        this.isModal = false;
        this.isFailModal = true;
        this.isLoading = false;
      });

    } else {
      this.isModal = false;
    }
  }

  closeSuccessModal(): void {
    this.isSuccessModal = false;
    this.route.navigate([], {relativeTo: this.activatedRoute, queryParams: {...this.params, afc: Math.random()}});
  }

  closeFailModal(): void {
    this.isFailModal = false;
  }
}
