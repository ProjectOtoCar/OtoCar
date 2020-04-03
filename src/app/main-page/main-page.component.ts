import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {QueryParamsAdminPage} from "../interfaces/QueryParamsAdminPage";
import {Subscription} from "rxjs";
import {SerachAdminFormService} from "../services/serach-admin-form.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  searchForm: FormGroup;
  accountsTypes: [string];
  @Output() queryParams = new EventEmitter<QueryParamsAdminPage>();
  accountTypesSub: Subscription;
  constructor(
    private searchAdminFormService: SerachAdminFormService,
    private route: Router,
    private activatedRoute: ActivatedRoute
  ) {
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
    this.accountTypesSub = this.searchAdminFormService.getAccountTypes().subscribe((data: [string]) => {
      this.accountsTypes = data;
    });
  }
  onSubmit(): void {
    this.queryParams.emit(this.searchForm.value);
  }
  clear(): void {
    this.searchForm.reset();
    this.onSubmit();
  }

}
