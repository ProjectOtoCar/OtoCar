import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-search-add-form',
  templateUrl: './search-add-form.component.html',
  styleUrls: ['./search-add-form.component.scss']
})
export class SearchAddFormComponent implements OnInit {
  searchAddForm: FormGroup;
  constructor() {
    this.searchAddForm = new FormGroup({
      brandName: new FormControl(null),
      modelName: new FormControl(null),
      lowPrice: new FormControl(null),
      highPrice: new FormControl(null),
      lowRegistration: new FormControl(null),
      highRegistration: new FormControl(null),
      orderBy: new FormControl(null)
    });
   }

  ngOnInit(): void {
  }

}
