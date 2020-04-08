import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CustomValidators } from 'src/app/validators/CustomValidators';
import { ActivatedRoute, Params } from '@angular/router';
import { UserPageService } from 'src/app/services/user-page.service';
import { Subscription } from 'rxjs';
import { Seller } from 'src/app/interfaces/Seller';

@Component({
  selector: 'app-modify-profile',
  templateUrl: './modify-profile.component.html',
  styleUrls: ['./modify-profile.component.scss']
})
export class ModifyProfileComponent implements OnInit, OnDestroy {
  editForm: FormGroup;
  subscription: Subscription;
  id: number;
  constructor(
    private activatedRoute: ActivatedRoute,
    private userPageService: UserPageService
    ) { }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.editForm = new FormGroup({
      firstName: new FormControl(null,
        [
          Validators.required,
          Validators.maxLength(40),
          Validators.minLength(3),
          CustomValidators.withoutSpace
        ]),
        lastName: new FormControl(null,
        [
          Validators.required,
          Validators.maxLength(40),
          Validators.minLength(3),
          CustomValidators.withoutSpace
        ]),
      phoneNumber: new FormControl(null,
        [
          Validators.required,
          Validators.maxLength(9),
          CustomValidators.phoneNumber,
          CustomValidators.withoutSpace
        ])
    });
    this.subscription = this.activatedRoute.queryParams
    .subscribe((params: Params) => {
      this.id =  params.userId;
      this.userPageService.downloadUserData(this.id)
      .subscribe((seller: Seller) => {
        this.editForm.setValue({
          firstName: seller.firstName,
          lastName: seller.lastName,
          phoneNumber: seller.phoneNumber
        });
      });
    });
  }

  onSubmit(): void {
    this.userPageService.modifyDane(this.id, this.editForm.value)
    .subscribe();
  }
}
