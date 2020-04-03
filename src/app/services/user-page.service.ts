import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Seller } from '../interfaces/Seller';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserPageService {

  constructor(private http: HttpClient) { }

  downloadUserData(id: number): Observable<Seller> {
    return this.http.get<Seller>(`http://localhost:8080/api/seller/${id}`);
  }
}
