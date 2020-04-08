import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Seller } from '../interfaces/Seller';
import { Observable, Subject, BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';
import { EditSeller } from '../interfaces/EditSeller';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserPageService {
  isUserFound = new Subject<boolean>();
  sellerSubject = new BehaviorSubject<Seller>(null);
  isLoading = new Subject<boolean>();
  isError = new Subject<boolean>();
  constructor(private http: HttpClient) { }

  downloadUserData(id: number): Observable<Seller> {
    return this.http.get<Seller>(`${environment.basicUrl}/api/seller/${id}`)
    .pipe(map((seller: Seller) => {
      seller.isPremium = Date.parse(String(seller.premiumAccount)) >= Date.now();
      return seller;
    }));
  }

  modifyDane(id: number, data: EditSeller): Observable<any> {
    return this.http.patch(`${environment.basicUrl}/api/seller/${id}`, data);
  }

  buyPremium(id: number, days: number): Observable<any> {
    return this.http.patch(`${environment.basicUrl}/api/seller/premium/${id}`, {days});
  }
}
