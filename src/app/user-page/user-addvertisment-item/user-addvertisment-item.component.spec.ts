import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAddvertismentItemComponent } from './user-addvertisment-item.component';

describe('UserAddvertismentItemComponent', () => {
  let component: UserAddvertismentItemComponent;
  let fixture: ComponentFixture<UserAddvertismentItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserAddvertismentItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAddvertismentItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
