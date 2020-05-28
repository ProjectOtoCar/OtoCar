import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginUser } from 'src/app/interfaces/loginUser.model';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { Token } from 'src/app/interfaces/Token.modal';
import { SellerPost } from 'src/app/interfaces/SellerPost.model';
import { Seller } from 'src/app/interfaces/Seller';

@Injectable({
  providedIn: 'root'
})
export class LoginUserService {

  loginUser = new BehaviorSubject(null);
  token = new BehaviorSubject(null);
  constructor(private http: HttpClient) { }

  signIn(login): Observable<any> {
    return this.http.post(`${environment.loginUrl}/api/user`, login)
    .pipe(map((token: Token) => {
      const [key, id] = this.getIdAndKey(token);
      this.getSellerByUserId(id)
        .subscribe((seller: Seller) => {
          return this.getEmailByAuthId(id)
            .subscribe((email: string) => {
              return this.getRoleByAuthId(id)
                .subscribe((role: string) => {
                  const loginUser = {id: seller.id, role, email} as LoginUser;
                  localStorage.setItem('user', JSON.stringify(loginUser));
                  this.loginUser.next(loginUser);
                  this.token.next(key);
                  localStorage.setItem('token', key);
                  return token;
                });
            });
        });
    }));
  }

  signOut(): void {
    this.loginUser.next(null);
    this.token.next(null);
    localStorage.removeItem('user');
    localStorage.removeItem('token');
  }

  autoSignIn(): void {
    const token = localStorage.getItem('token');
    const loginUser = JSON.parse(localStorage.getItem('user'));
    this.loginUser.next(loginUser);
    this.token.next(token);
  }

  public patchAppUSer(authId: number, changes: any): Observable<any> {
    return this.http
      .patch(`${environment.loginUrl}/api/user/${authId}`, changes);
  }
  createUser(sellerPost: SellerPost): Observable<any> {
    sellerPost.registerUser.role = 'ROLE_USER';
    console.log(sellerPost);
    return this.http.post(`${environment.loginUrl}/api/user/regi`, sellerPost.registerUser);
  }

  getSellerByUserId(userId: number): Observable<Seller> {
    return this.http
      .get<Seller>(`${environment.basicUrl}/api/seller/authId/${userId}`);
  }
  deleteUserById(authId: number): Observable<any> {
    return this.http
    .delete(`${environment.loginUrl}/api/user/${authId}`);
  }

  getEmailByAuthId(authId: number): Observable<string> {
    return this.http
      .get<{email: string}>(`${environment.loginUrl}/api/user/email/${authId}`)
      .pipe(map((email: {email: string}) => email.email));
  }

  getRoleByAuthId(authId: number): Observable<string> {
    return this.http
      .get<{role: string}>(`${environment.loginUrl}/api/user/role/${authId}`)
      .pipe(map((role: {role: string}) => {
        return this.translateRole(role.role);
      }))
      ;
  }

  private getIdAndKey(token: Token): [string, number] {
    let i = 0;
    while (token.key[i] !== ' ') {
      i++;
    }
    const key = token.key.substr(i);
    const id = token.key.slice(0, i);
    return [key, +id];
  }

  private translateRole(role: string): string {
    return role.substr(5);
  }
}

