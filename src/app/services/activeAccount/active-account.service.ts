import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ActiveAccountService {

  constructor(
    private http: HttpClient
  ) { }

   activeAccount(token: string): Observable<any> {
     const key = this.getKey(token);
     return this.http
        .get(`${environment.loginUrl}/api/user/verifyToken?token=${key}`);
   }

   private getKey(token: string): string {
    let i = 0;
    while (token[i] !== '_') {
      i++;
    }
    const key = token.substr(i + 1);
    return key;
  }
}
