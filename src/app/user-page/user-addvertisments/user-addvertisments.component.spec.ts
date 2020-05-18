import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAddvertismentsComponent } from './user-addvertisments.component';

describe('UserAddvertismentsComponent', () => {
  let component: UserAddvertismentsComponent;
  let fixture: ComponentFixture<UserAddvertismentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserAddvertismentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAddvertismentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
