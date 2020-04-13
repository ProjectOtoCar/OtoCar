import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-add-addvertisment',
  templateUrl: './add-addvertisment.component.html',
  styleUrls: ['./add-addvertisment.component.scss']
})
export class AddAddvertismentComponent implements OnInit {
  addAddvertismentForm: FormGroup;
  isLoading = false;
  isError = false;
  currentYear: number;
  constructor() {
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
          id: new FormControl(null)
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
   onSubmit(): void {
    console.log(this.addAddvertismentForm.value);
   }

  onChangeMainImage(index: number): void {
    (this.addAddvertismentForm.get('images') as FormArray)
    .controls.forEach((imageItem, i: number) => {
        imageItem.patchValue({
          isMainImage: i === index ? true : false
        });
    });
  }

  addImage(): void {
    const control = new FormGroup({
      photo: new FormControl(null),
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
  ngOnInit(): void {
  }

}
