import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CustomValidators } from 'src/app/validators/CustomValidators';
import { ActivatedRoute, Params, Router } from '@angular/router';
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
  queryParams;
  id: number;
  isError: boolean;
  isChange: boolean;
  isSending: boolean;
  isLoading: boolean;
  isLoadingError: boolean;
  constructor(
    private activatedRoute: ActivatedRoute,
    private route: Router,
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
      this.isLoading = true;
      this.isLoadingError = false;
      this.id =  params.userId;
      this.queryParams = params;
      this.userPageService.downloadUserData(this.id)
      .subscribe((seller: Seller) => {
        this.isLoading = false;
        this.editForm.setValue({
          firstName: seller.firstName,
          lastName: seller.lastName,
          phoneNumber: seller.phoneNumber
        });
      }, error => {
        this.isLoading = false;
        this.isLoadingError = true;
      });
    });
  }

  onSubmit(): void {
    this.isSending = true;
    this.isError = false;
    this.isChange = false;
    this.userPageService.modifyDane(this.id, this.editForm.value)
    .subscribe((params: Params) => {
      this.isSending = false;
      this.isError = false;
      this.isChange = true;
      this.route.navigate([], {relativeTo: this.activatedRoute, queryParams: {...this.queryParams, afc: Math.random()}});
    }, error => {
      this.isSending = false;
      this.isError = true;
      this.isChange = false;
    });
  }
}
