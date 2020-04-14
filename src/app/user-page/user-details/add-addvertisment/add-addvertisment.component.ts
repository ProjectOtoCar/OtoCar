import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, AbstractControl } from '@angular/forms';
import { AddvertismentService } from 'src/app/services/addvertisment/addvertisment.service';
import {CarModelService} from 'src/app/services/car-model/car-model.service';
import { EnumsService } from 'src/app/services/enums/enums.service';
import { BrandService } from 'src/app/services/brand/brand.service';
import { ActivatedRoute, Event } from '@angular/router';
import { strict } from 'assert';
import { Brand } from 'src/app/interfaces/Brand.model';
import { CarModel } from 'src/app/interfaces/CarModel.model';
import { CityService } from 'src/app/services/city/city.service';
import { City } from 'src/app/interfaces/City.modal';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-add-addvertisment',
  templateUrl: './add-addvertisment.component.html',
  styleUrls: ['./add-addvertisment.component.scss']
})
export class AddAddvertismentComponent implements OnInit, OnDestroy {
  addAddvertismentForm: FormGroup;
  isLoading = false;
  isError = false;
  currentYear: number;
  isTypeFuelLoading = false;
  isTypeFuelError = false;
  TypeFuels: [string];
  isColorLoading = false;
  isColorError = false;
  colors: [string];
  isTypeCarLoading = false;
  isTypeCarError = false;
  typeCars: [string];
  isBrandLoading = false;
  isBrandError = false;
  brands: [Brand];
  selectBrandIndex: number;
  isCarModelLoading = false;
  isCarModelError = false;
  carModels: [CarModel];
  isCityLoading = false;
  isCityError = false;
  cities: [City];
  citySub: Subscription;
  carModelSub: Subscription;
  brandSub: Subscription;
  colorSub: Subscription;
  typyFuelSub: Subscription;
  typeCarSub: Subscription;

  constructor(
    private addvertismentService: AddvertismentService,
    private carModelService: CarModelService,
    private enumsService: EnumsService,
    private brandService: BrandService,
    private cityService: CityService
    ) {
    this.currentYear = new Date().getFullYear();
    this.addAddvertismentForm = new FormGroup({
      title: new FormControl(null,
        [
          Validators.required,
          Validators.maxLength(100),
          Validators.minLength(10),
        ]
      ),
      price: new FormControl(null,
          [
            Validators.required,
            Validators.max(10000000),
            Validators.min(0)
          ]
      ),
      content: new FormControl(null,
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(500),
        ]
      ),
      car: new FormGroup(
        {
          brand: new FormGroup(
            {
              id: new FormControl(null,
                [
                  Validators.required
                ]
              )
            }
          ),
          model: new FormGroup(
            {
              id: new FormControl(null,
                [
                  Validators.required
                ]
              )
            }
          ),
          engine: new FormControl(null,
            [
              Validators.required,
              Validators.max(50_000),
              Validators.min(1)
            ]
          ),
          enginePower: new FormControl(null,
            [
              Validators.required,
              Validators.max(20_000),
              Validators.min(1)
            ]
          ),
          fuel: new FormControl(null,
            [
              Validators.required
            ]
          ),
          typeCar: new FormControl(null,
            [
              Validators.required
            ]
          ),
          mileage: new FormControl(null,
            [
              Validators.required,
              Validators.min(0),
              Validators.max(10_000_000)
            ]
          ),
          firstRegistartion: new FormControl(null,
            [
              Validators.required,
              Validators.min(1900),
              Validators.max(this.currentYear)
            ]
          ),
          color: new FormControl(null,
            [
              Validators.required
            ]
          )
        }
      ),
      seller: new FormGroup(
        {
          id: new FormControl(6)
        }
      ),
      city: new FormGroup(
        {
          id: new FormControl(null,
            [
              Validators.required
            ]
          )
        }
      ),
      images: new FormArray([])
    });
   }
  ngOnDestroy(): void {
    this.citySub?.unsubscribe();
    this.carModelSub?.unsubscribe();
    this.brandSub?.unsubscribe();
    this.colorSub?.unsubscribe();
    this.typyFuelSub?.unsubscribe();
    this.typeCarSub?.unsubscribe();
  }

   ngOnInit(): void {
      this.isTypeFuelLoading = true;
      this.isTypeFuelError = false;
      this.isColorLoading = true;
      this.isColorError = false;
      this.isTypeCarLoading = true;
      this.isTypeCarError = false;
      this.isBrandLoading = true;
      this.isBrandError = false;
      this.isCityLoading = true;
      this.isCityError = false;
      this.typyFuelSub = this.enumsService.getTypeFuel()
       .subscribe((typeFuels: [string]) => {
         this.isTypeFuelLoading = false;
         this.isTypeFuelError = false;
         this.TypeFuels = typeFuels;
       },  error => {
         this.isTypeFuelLoading = false;
         this.isTypeFuelError = true;
       });
      this.colorSub = this.enumsService.getColors()
      .subscribe((colors: [string]) => {
        this.isColorLoading = false;
        this.isColorError = false;
        this.colors = colors;
      }, error => {
        this.isColorLoading = false;
        this.isColorError = true;
      });
      this.typeCarSub = this.enumsService.getTypeCar()
      .subscribe((typeCars: [string]) => {
        this.isTypeCarError = false;
        this.isTypeCarLoading = false;
        this.typeCars = typeCars;
      }, error => {
        this.isTypeCarLoading = false;
        this.isTypeCarError = true;
      });
      this.brandService.getBrand()
      .subscribe((brands: [Brand]) => {
        this.isBrandLoading = false;
        this.isBrandError = false;
        this.brands = brands;
      }, error => {
        this.isBrandLoading = false;
        this.isBrandError = true;
      });
      this.citySub = this.cityService.getCities()
      .subscribe((cities: [City]) => {
        this.isCityError = false;
        this.isCityLoading = false;
        this.cities = cities;
      }, error => {
        this.isCityError = true;
        this.isCityLoading = false;
      });
    }
  onChangeBrand(event) {
    this.selectBrandIndex = event;
    this.isCarModelLoading = true;
    this.isCarModelError = false;
    this.carModelSub = this.carModelService.getCarModels(this.brands[event - 1].name)
    .subscribe((carModels: [CarModel]) => {
      this.isCarModelLoading = false;
      this.isCarModelError = false;
      this.carModels = carModels;
    }, error => {
      this.isCarModelLoading = false;
      this.isCarModelError = false;
    });
  }

   onSubmit(): void {
    this.isLoading = true;
    this.isError = false;
    console.log(this.addAddvertismentForm.value);
    this.addvertismentService
    .postAddvertisment(this.addAddvertismentForm.value)
    .subscribe(() => {
      this.isLoading = false;
      this.isError = false;
    }, error => {
      this.isError = true;
      this.isLoading = false;
    });
   }
   onImageChange(event, index: number): void {
    if ((event.target as HTMLInputElement).files.length > 0) {
      const file = (event.target as HTMLInputElement).files[0];
      console.log(file);
      (this.addAddvertismentForm.get('images') as FormArray)
      .controls[index].patchValue({
        photo: file
      });
    }
   }
   onDeleteImage(index: number): void {
     const imageItem = (this.addAddvertismentForm.get('images') as FormArray)
     .controls
     .splice(index, 1);
     if (imageItem[0].value.isMainImage
        && (this.addAddvertismentForm.get('images') as FormArray).controls.length > 0) {
        (this.addAddvertismentForm.get('images') as FormArray)
          .controls[0]
          .patchValue({
            isMainImage: true
          });
     }

   }
  onChangeMainImage(index: number): void {
    (this.addAddvertismentForm.get('images') as FormArray)
    .controls.forEach((imageItem, i: number) => {
        imageItem.patchValue({
          isMainImage: i === index ? true : false
        });
    });
  }

  checkImageIsValid(index: number): boolean {
    return ((this.addAddvertismentForm.get('images') as FormArray)
    .controls[index] as FormGroup)
    .controls
    .photo
    .valid;
  }

  checkImageIsTouched(index: number): boolean {
    return ((this.addAddvertismentForm.get('images') as FormArray)
    .controls[index] as FormGroup)
    .controls
    .photo
    .touched;
  }

  addImage(): void {
    const control = new FormGroup({
      photo: new FormControl(null,
        [
          Validators.required
        ]
      ),
      isMainImage: new FormControl(false)
    });
    if ((this.addAddvertismentForm.get('images') as FormArray).length < 1) {
      control.setValue({
        photo: null,
        isMainImage: true
      });
    }
    (this.addAddvertismentForm.get('images') as FormArray).push(control);
  }
  getImages(): AbstractControl[] {
    return (this.addAddvertismentForm.get('images') as FormArray).controls;
  }
}
