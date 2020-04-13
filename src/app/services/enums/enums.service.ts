import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class EnumsService {

  constructor(private http: HttpClient) { }

  public getColors(): Observable<[string]> {
    return this.http
    .get<[string]>(`${environment.basicUrl}/api/enums/color`);
  }

  public getTypeAccount(): Observable<[string]> {
    return this.http
    .get<[string]>(`${environment.basicUrl}/api/enums/account`);
  }

  public getTypeCar(): Observable<[string]> {
    return this.http
    .get<[string]>(`${environment.basicUrl}/api/enums/typeCar`);
  }

  public getTypeFuel(): Observable<[string]> {
    return this.http
    .get<[string]>(`${environment.basicUrl}/api/enums/typeFuel`);
  }


}
