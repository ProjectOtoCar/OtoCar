import { TestBed } from '@angular/core/testing';

import { ActiveAccountService } from './active-account.service';

describe('ActiveAccountService', () => {
  let service: ActiveAccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActiveAccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
