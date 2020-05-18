import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertismentModifyComponent } from './advertisment-modify.component';

describe('AdvertismentModifyComponent', () => {
  let component: AdvertismentModifyComponent;
  let fixture: ComponentFixture<AdvertismentModifyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvertismentModifyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvertismentModifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
