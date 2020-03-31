import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Seller } from '../interfaces/Seller';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AdminPanelService {

  constructor(private http: HttpClient) { }
  deleteSeller(id: number) {
    return this.http.delete(`http://localhost:8080/api/seller/${id}`);
  }
  getSellers(
    page: number,
    firstname?: string,
    lastname?: string,
    type?: string,
    premium?: string,
    sort?: string): Observable<Seller[]> {
      if (page < 0 || page === null || page === undefined) {
        page = 1;
      }
      let searchParams = new HttpParams();
      searchParams = searchParams.append('page', String(page));
      if (firstname !== null && firstname !== undefined && firstname !== '') {
          searchParams = searchParams.append('firstName', firstname);
      }
      if (lastname !== null && firstname !== undefined) {
        searchParams = searchParams.append('lastName', lastname);
      }
      if (type !== null && type !== undefined) {
        searchParams = searchParams.append('type', type);
      }
      if (premium !== null && premium !== undefined) {
        searchParams = searchParams.append('premium', premium);
      }
      if (sort !== null && sort !== undefined) {
        searchParams = searchParams.append('sort', sort);
      }
      return this.http.get<Seller[]>('http://localhost:8080/api/seller',
      {
        params: searchParams
      }).pipe(map((data: any) => {
        const sellerPage: Seller[] = data.content;
        console.log(data);
        for (const seller of sellerPage) {
          seller.isPremium = Date.parse(String(seller.premiumAccount)) >= Date.now();
        }
        return sellerPage;
      }));
  }
}
