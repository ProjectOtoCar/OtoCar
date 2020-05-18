import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OneAdvertismentComponent } from './one-advertisment.component';

describe('OneAdvertismentComponent', () => {
  let component: OneAdvertismentComponent;
  let fixture: ComponentFixture<OneAdvertismentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OneAdvertismentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OneAdvertismentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
