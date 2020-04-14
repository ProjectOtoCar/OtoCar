import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from 'src/environments/environment';
import { City } from 'src/app/interfaces/City.modal';
@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private http: HttpClient) { }

  public getCities(): Observable<[City]> {
    return this.http
    .get<[City]>(`${environment.basicUrl}/api/city`);
  }
}
