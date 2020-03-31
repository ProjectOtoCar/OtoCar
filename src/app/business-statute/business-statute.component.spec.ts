import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessStatuteComponent } from './business-statute.component';

describe('BusinessStatuteComponent', () => {
  let component: BusinessStatuteComponent;
  let fixture: ComponentFixture<BusinessStatuteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BusinessStatuteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessStatuteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
