import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TelLinkComponent } from './tel-link.component';

describe('TelLinkComponent', () => {
  let component: TelLinkComponent;
  let fixture: ComponentFixture<TelLinkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TelLinkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TelLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
