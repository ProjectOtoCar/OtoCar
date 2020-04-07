import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltrAdvertismentComponent } from './filtr-advertisment.component';

describe('FiltrAdvertismentComponent', () => {
  let component: FiltrAdvertismentComponent;
  let fixture: ComponentFixture<FiltrAdvertismentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiltrAdvertismentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiltrAdvertismentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
