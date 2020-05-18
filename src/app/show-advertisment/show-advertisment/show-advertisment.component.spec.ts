import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAdvertismentComponent } from './show-advertisment.component';

describe('ShowAdvertismentComponent', () => {
  let component: ShowAdvertismentComponent;
  let fixture: ComponentFixture<ShowAdvertismentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowAdvertismentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAdvertismentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
