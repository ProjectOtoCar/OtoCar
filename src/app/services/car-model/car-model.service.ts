import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import {CarModel} from '../../interfaces/CarModel.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CarModelService {

  constructor(private http: HttpClient) { }

  public getCarModels(brand: string): Observable<[CarModel]> {
    let queryParams = new HttpParams();
    queryParams =  queryParams.append('brand', brand);
    return this.http
    .get<[CarModel]>(`${environment.basicUrl}/api/carModel`,
    {
      params: queryParams
    });
  }
}
