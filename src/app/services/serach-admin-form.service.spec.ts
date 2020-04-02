import { TestBed } from '@angular/core/testing';

import { SerachAdminFormService } from './serach-admin-form.service';

describe('SerachAdminFormService', () => {
  let service: SerachAdminFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SerachAdminFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
