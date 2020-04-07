import { Component, OnInit, Output, EventEmitter, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SerachAdminFormService } from 'src/app/services/serach-admin-form.service';
import { QueryParamsAdminPage } from 'src/app/interfaces/QueryParamsAdminPage';
import { Subscription } from 'rxjs';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.scss']
})
export class SearchFormComponent implements OnInit, OnDestroy {
  searchForm: FormGroup;
  accountsTypes: [string];
  isLoading: boolean;
  isError: boolean;
  @Output() queryParams = new EventEmitter<QueryParamsAdminPage>();
  accountTypesSub: Subscription;

  constructor(
    private searchAdminFormService: SerachAdminFormService,
    private activatedRoute: ActivatedRoute) {
    this.searchForm = new FormGroup({
      firstName: new FormControl(null,
      [
        Validators.maxLength(100)
      ]),
      lastName: new FormControl(null,
      [
        Validators.maxLength(100)
      ]),
      type: new FormControl(null),
      premium: new FormControl(null),
      sort: new FormControl('')
    });
   }
  ngOnDestroy(): void {
    this.accountTypesSub.unsubscribe();
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      this.isLoading = true;
      this.isError = false;
      this.accountTypesSub = this.searchAdminFormService.getAccountTypes().subscribe((data: [string]) => {
        this.accountsTypes = data;
        this.isLoading = false;
      }, error => {
      this.isLoading = false;
      this.isError = true;
      });
    });
  }
  onSubmit(): void {
    this.queryParams.emit(this.searchForm.value);
  }
  clear(): void {
    this.searchForm.reset();
    this.onSubmit();
  }
  random(): number {
    return Math.random();
  }

}
