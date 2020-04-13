import { TestBed } from '@angular/core/testing';

import { AddvertismentService } from './addvertisment.service';

describe('AddvertismentService', () => {
  let service: AddvertismentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddvertismentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
