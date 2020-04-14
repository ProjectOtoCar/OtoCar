import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAddvertismentComponent } from './add-addvertisment.component';

describe('AddAddvertismentComponent', () => {
  let component: AddAddvertismentComponent;
  let fixture: ComponentFixture<AddAddvertismentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAddvertismentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAddvertismentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
