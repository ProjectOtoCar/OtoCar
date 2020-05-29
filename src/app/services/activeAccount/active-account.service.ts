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

   activeAccout(token: string): Observable<any> {
     return this.http
        .get(`${environment}/api/user/verifyToken?token=${token}`);
   }
}
