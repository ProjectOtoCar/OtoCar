import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Addvertisment } from 'src/app/interfaces/Addvertisment';
import { environment } from '../../../environments/environment';
import { ShortAddvertisment } from 'src/app/interfaces/ShortAddvertisment.model';
import { map } from 'rxjs/operators';
import { QueryParamsAdvertismentSearch } from 'src/app/interfaces/QueryParamsAdvertismentSearch.model';

@Injectable({
  providedIn: 'root'
})
export class AddvertismentService {

  constructor(private http: HttpClient) { }

  postAddvertisment(add: Addvertisment): Observable<Addvertisment> {
    return this.http
    .post<Addvertisment>(`${environment.basicUrl}/api/advertisement`, add);
  }

  deleteAddvertisment(id: number): Observable<any> {
    return this.http
    .delete(`${environment.basicUrl}/api/advertisement/${id}`);
  }

  patchAddvertisment(id: number, body): Observable<any> {
    return this.http
    .patch(`${environment.basicUrl}/api/advertisement/${id}`, body);
  }

  putAdvertisment(id: number, body): Observable<any> {
    return this.http
    .put(`${environment.basicUrl}/api/advertisement/${id}`, body);
  }

  getAddvertismentBySeller(idSeller: number, page: number = 1): Observable<[ShortAddvertisment[], number]> {
    let searchParams = new HttpParams();
    searchParams = searchParams.append('page', '' + page);
    return this.http
    .get<[ShortAddvertisment[], number]>(`${environment.basicUrl}/api/advertisement/seller/${idSeller}`
    ,
    {
      params: searchParams
    }).pipe(map((data: any) => {
        const shortAddvertismentPage: ShortAddvertisment[] = data.content;
        return [shortAddvertismentPage, data.totalPages];
    }));
  }

  getAdvertisements(queryParams: QueryParamsAdvertismentSearch): Observable<[ShortAddvertisment[], number]> {
    let searchParams = new HttpParams();
    if (queryParams.brandName !== null && queryParams.brandName !== undefined && queryParams.brandName !== '') {
      searchParams = searchParams.append('brandName', queryParams.brandName);
    }
    if (queryParams.modelName !== null && queryParams.modelName !== undefined && queryParams.modelName !== '') {
      searchParams = searchParams.append('modelName', queryParams.modelName);
    }
    if (queryParams.highPrice !== null && queryParams.highPrice !== undefined && '' + queryParams.highPrice !== '') {
      searchParams = searchParams.append('highPrice', '' + queryParams.highPrice);
    }
    if (queryParams.lowPrice !== null && queryParams.lowPrice !== undefined && '' + queryParams.lowPrice !== '') {
      searchParams = searchParams.append('lowPrice', '' + queryParams.lowPrice);
    }
    if (queryParams.lowRegistration !== null && queryParams.lowRegistration !== undefined && '' + queryParams.lowRegistration !== '') {
      searchParams = searchParams.append('lowRegistration', '' + queryParams.lowRegistration);
    }
    if (queryParams.highRegistration !== null && queryParams.highRegistration !== undefined && '' + queryParams.highRegistration !== '') {
      searchParams = searchParams.append('highRegistration', '' + queryParams.highRegistration);
    }
    if (queryParams.orderBy !== null && queryParams.orderBy !== undefined && queryParams.orderBy !== '') {
      searchParams = searchParams.append('orderBy', queryParams.orderBy);
    }
    if (queryParams.page !== null && queryParams.page !== undefined && '' + queryParams.page !== '') {
      searchParams = searchParams.append('page', '' + queryParams.page);
    }
    return this.http
      .get<[ShortAddvertisment[], number]>(`${environment.basicUrl}/api/advertisement/sort`, {
        params: searchParams
      }).pipe(map((data: any) => {
        const shortAddvertismentPage: ShortAddvertisment[] = data.content;
        return [shortAddvertismentPage, data.totalPages];
    }));
  }
  getAdvertisement(id: number): Observable<Addvertisment> {
    return this.http
    .get<Addvertisment>(`${environment.basicUrl}/api/advertisement/${id}`)
    .pipe(map((add: Addvertisment) => {
      add.seller.isPremium = Date.parse(String(add.seller.premiumAccount)) >= Date.now();
      return add;
    }));
  }
}
