import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Seller } from 'src/app/interfaces/Seller';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SellerService {

  constructor(private http: HttpClient) { }

  public getSellerById(sellerId: number): Observable<Seller> {
    return this.http
      .get<Seller>(`${environment.basicUrl}/${sellerId}`);
  }

  public patchSeller(sellerId: number, changes: any): Observable<any> {
    return this.http
      .patch(`${environment.basicUrl}/api/seller/${sellerId}`, changes);
  }
}
