import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainstatuteComponent } from './main-statute.component';

describe('MainStatuteComponent', () => {
  let component: MainstatuteComponent;
  let fixture: ComponentFixture<MainstatuteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainstatuteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainstatuteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
