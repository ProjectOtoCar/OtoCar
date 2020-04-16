import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AddvertismentService } from 'src/app/services/addvertisment/addvertisment.service';
import {CarModelService} from 'src/app/services/car-model/car-model.service';
import { EnumsService } from 'src/app/services/enums/enums.service';
import { BrandService } from 'src/app/services/brand/brand.service';
import { Brand } from 'src/app/interfaces/Brand.model';
import { CarModel } from 'src/app/interfaces/CarModel.model';
import { CityService } from 'src/app/services/city/city.service';
import { City } from 'src/app/interfaces/City.modal';
import { Subscription } from 'rxjs';
import { Image } from 'src/app/interfaces/Image';

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
  images: Image[] = [];

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
          brandId: new FormControl(null,
            [
              Validators.required
            ]
          ),
          modelId: new FormControl(null,
            [
              Validators.required
            ]
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
          firstRegistration: new FormControl(null,
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
        sellerId: new FormControl(6),
        cityId: new FormControl(null,
          [
            Validators.required
          ]
      ),
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
    console.log({...this.addAddvertismentForm.value, images: this.images});
    this.addvertismentService
    .postAddvertisment({...this.addAddvertismentForm.value, images: this.images})
    .subscribe((data) => {
      this.isLoading = false;
      this.isError = false;
      console.log(data);
    }, error => {
      this.isError = true;
      this.isLoading = false;
    });
   }
   onImageChange(event, index: number): void {
    const reader = new FileReader();
    if (event.target?.files.length > 0) {
      const file: File = event.target.files[0];
      this.checkIsImageValid(file, index);
      reader.readAsDataURL(file);
      reader.onload = () => {
          this.images[index].photo = reader.result;
      };
    }
   }

   onDeleteImage(index: number): void {
     const imageItem = this.images
     .splice(index, 1);
     if (imageItem[0].mainImage
        && this.images.length > 0) {
        this.images[0].mainImage = true;
     }
   }

   private checkIsImageValid(file, index) {
    if (file.size > 4194304) {
      this.images[index].valid = {
        ... this.images[index].valid,
        wrongsizebig: true
      };
    }
    if (file.size <= 0) {
      this.images[index].valid = {
        ... this.images[index].valid,
        wrongsizesmall: true
      };
    }
    if (!file.type.startsWith('image')) {
      this.images[index].valid = {
        ... this.images[index].valid,
        wrongtype: true
      };
    }
   }

  onChangeMainImage(index: number): void {
    this.images
    .forEach((imageItem: Image, i: number) => {
        imageItem.mainImage = i === index ? true : false;
    });
  }

  checkImageIsValid(index: number): boolean {
    return this.images[index].valid === undefined
           && this.images[index].photo !== undefined
           && this.images[index].photo !== null
           && this.images[index].photo !== '';
  }

  checkImagesIsValid(): boolean {
    let isValid = true;
    this.images.forEach((itemImage, index) => {
      if (!this.checkImageIsValid(index)) {
        isValid = false;
      }
    });
    console.log(isValid);
    return isValid;
  }


  addImage(): void {
    if (this.images.length < 1) {
      this.images.push({photo: null, mainImage: true});
    } else {
      this.images.push({photo: null, mainImage: false});
    }
  }
}
