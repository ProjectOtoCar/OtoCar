import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemAdminPanelComponent } from './item-admin-panel.component';

describe('ItemAdminPanelComponent', () => {
  let component: ItemAdminPanelComponent;
  let fixture: ComponentFixture<ItemAdminPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemAdminPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemAdminPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
