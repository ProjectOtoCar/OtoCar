import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ResetPasswordService {

  constructor(private http: HttpClient) { }

  sendEmailWithUsername(username: string): Observable<any> {
    return this.http
      .post(`${environment.loginUrl}/api/user/reset?username=${ username }`, { username });
  }
  changePassword(token, password): Observable<any> {
    return this.http
      .post(`${environment.loginUrl}/upi/user/token=${ token }`, { password });
  }
}
