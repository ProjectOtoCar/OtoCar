import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Addvertisment } from 'src/app/interfaces/Addvertisment';
import { environment } from '../../../environments/environment';
import { ShortAddvertisment } from 'src/app/interfaces/ShortAddvertisment.model';
import { map } from 'rxjs/operators';

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

  getAddvertismentBySeller(idSeller: number, page: number = 1): Observable<[ShortAddvertisment[], number]> {
    let searchParams = new HttpParams();
    searchParams = searchParams.append('page', '' + page);
    return this.http
    .get<[ShortAddvertisment[], number]>(`${environment.basicUrl}/api/advertisement/seller/${idSeller}`
    ,
    {
      params: searchParams
    }).pipe(map((data: any) => {
        const shortAddvertismentPage: [ShortAddvertisment] = data.content;
        return [shortAddvertismentPage, data.totalPages];
    }));
  }
}
