import { Component, OnInit, OnDestroy, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { CarModelService } from 'src/app/services/car-model/car-model.service';
import { BrandService } from 'src/app/services/brand/brand.service';
import { Subscription } from 'rxjs';
import { CarModel } from 'src/app/interfaces/CarModel.model';
import { Brand } from 'src/app/interfaces/Brand.model';
import { QueryParamsAdvertismentSearch } from 'src/app/interfaces/QueryParamsAdvertismentSearch.model';

@Component({
  selector: 'app-search-add-form',
  templateUrl: './search-add-form.component.html',
  styleUrls: ['./search-add-form.component.scss']
})
export class SearchAddFormComponent implements OnInit, OnDestroy {
  @Output() queryParams = new EventEmitter<QueryParamsAdvertismentSearch>();
  @Input() isMainPage = true;
  @Input() params: QueryParamsAdvertismentSearch;

  searchAddForm: FormGroup;
  isError = false;
  isCarModelLoading = false;
  isCarModelError = false;
  selectBrandIndex: number;
  isBrandLoading = false;
  isBrandError = false;
  brands: [Brand];
  carModels: [CarModel];
  lowPriceStart = 1;
  lowPrice = this.lowPriceStart;
  highPriceStart = 100_000_000;
  highPrice = this.highPriceStart;
  lowRegistrationStart = 1900;
  lowRegistration = this.lowRegistrationStart;
  highRegistrationStart = new Date().getFullYear();
  highRegistration = this.highRegistrationStart;
  carModelSub: Subscription;
  brandSub: Subscription;


  constructor(
    private carModelService: CarModelService,
    private brandService: BrandService,
    ) {
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
    this.searchAddForm.patchValue({
      lowPrice: this.lowPrice,
      highPrice: this.highPrice,
      lowRegistration: this.lowRegistration,
      highRegistration: this.highRegistration,
      orderBy: 'desc'
    });

    this.isBrandLoading = true;
    this.isBrandError = false;
    this.brandService.getBrand()
   .subscribe((brands: [Brand]) => {
     this.isBrandLoading = false;
     this.isBrandError = false;
     this.brands = brands;
     if (!this.isMainPage) {
      let brandId: number;
      this.brands?.forEach((brand: Brand) => {
        if (this.params.brandName === brand.name) {
          brandId = brand.id;
        }
      });
      this.searchAddForm.patchValue({
        brandName: brandId || undefined,
        lowPrice: this.params.lowPrice || this.lowPriceStart,
        highPrice: this.params.highPrice || this.highPriceStart,
        lowRegistration: this.params.lowRegistration || this.lowRegistrationStart,
        highRegistration: this.params.highRegistration || this.highRegistrationStart,
        orderBy: this.params.orderBy || 'desc'
      });
      this.carModelService.getCarModels(this.params.brandName)
      .subscribe((carModels: [CarModel]) => {
        let carModelId: number;
        this.carModels = carModels;
        this.carModels?.forEach((brand: Brand) => {
          if (this.params.modelName === brand.name) {
            carModelId = brand.id;
          }
        });
        this.searchAddForm.patchValue(
          {
            modelName: carModelId
          }
        );
        this.isCarModelLoading = false;
        this.isCarModelError = false;
        this.carModels = carModels;
      }, error => {
        this.isCarModelLoading = false;
        this.isCarModelError = false;
      });
    }
   }, error => {
     this.isBrandLoading = false;
     this.isBrandError = true;
   });
  }

  onSubmit(): void {
    const value: QueryParamsAdvertismentSearch = this.searchAddForm.value;
    this.brands?.forEach((brand: Brand) => {
      if (brand.id === +value.brandName) {
        value.brandName = brand.name;
      }
    });
    this.carModels?.forEach((carModel: CarModel) => {
      if (carModel.id === +value.modelName) {
        value.modelName = carModel.name;
      }
    });
    value.lowPrice = this.lowPrice;
    value.highPrice = this.highPrice;
    value.lowRegistration = this.lowRegistration;
    value.highRegistration = this.highRegistration;
    this.queryParams.emit(value);
  }

  cleanForm(): void {
    this.searchAddForm.reset();
    this.searchAddForm.patchValue({
      lowPrice: this.lowPriceStart,
      highPrice: this.highPriceStart,
      lowRegistration: this.lowRegistrationStart,
      highRegistration: this.highRegistrationStart,
      orderBy: 'desc'
    });
  }

  onChangeBrand(event) {
    this.selectBrandIndex = event;
    this.isCarModelLoading = true;
    this.isCarModelError = false;
    this.carModelSub = this.carModelService.getCarModels(this.brands[event - 1].name)
    .subscribe((carModels: [CarModel]) => {
      this.searchAddForm.patchValue(
        {
          modelName: undefined
        }
      );
      this.isCarModelLoading = false;
      this.isCarModelError = false;
      this.carModels = carModels;
    }, error => {
      this.isCarModelLoading = false;
      this.isCarModelError = false;
    });
  }

  setLowPrice(event: number): void {
    if (event > this.highPrice || event < this.lowPriceStart) {
      return;
    }
    this.lowPrice = +event;
  }

  setHighPrice(event: number): void {
    if (event < this.lowPrice || event > this.highPriceStart) {
      return;
    }
    this.highPrice = +event;
  }

  setlowRegistration(event: number): void {
    if (event > this.highRegistration || event < this.lowRegistrationStart) {
      return;
    }
    this.lowRegistration = event;
  }

  setHighRegistration(event: number): void {
    if (event < this.lowRegistration || event > this.highRegistrationStart) {
      return;
    }
    this.highRegistration = event;
  }

  ngOnDestroy(): void {
    this.carModelSub?.unsubscribe();
    this.brandSub?.unsubscribe();
  }
}
