import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchAddFormComponent } from './search-add-form.component';

describe('SearchAddFormComponent', () => {
  let component: SearchAddFormComponent;
  let fixture: ComponentFixture<SearchAddFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchAddFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchAddFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
