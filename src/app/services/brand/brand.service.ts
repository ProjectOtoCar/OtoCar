import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import {Brand} from '../../interfaces/Brand.model';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class BrandService {

  constructor(private http: HttpClient) { }

  public getBrand(): Observable<[Brand]> {
    return this.http
    .get<[Brand]>(`${environment.basicUrl}/api/brand`);
  }
}
