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
      console.log(key);
      this.getSellerByUserId(id)
        .subscribe((seller: Seller) => {
          console.log(seller);
          return this.getEmailByUserId(id)
            .subscribe((email: {email: string}) => {
              return this.getRoleByUserId(id)
                .subscribe((role: string) => {
                  const loginUser = {id: seller.id, role, email: email.email} as LoginUser;
                  localStorage.setItem('user', JSON.stringify(loginUser));
                  console.log(key, id, seller);
                  this.loginUser.next(loginUser);
                  this.token.next(key);
                  localStorage.setItem('token', key);
                  console.log(loginUser);
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

  createUser(sellerPost: SellerPost): Observable<any> {
    sellerPost.registerUser.role = 'ROLE_USER';
    console.log(sellerPost);
    return this.http.post(`${environment.loginUrl}/api/user/regi`, sellerPost.registerUser);
  }

  getSellerByUserId(userId: number): Observable<Seller> {
    return this.http
      .get<Seller>(`${environment.basicUrl}/api/seller/authId/${userId}`);
  }

  getEmailByUserId(userId: number): Observable<{email: string}> {
    return this.http
      .get<{email: string}>(`${environment.loginUrl}/api/user/email/${userId}`);
  }

  getRoleByUserId(userId: number): Observable<string> {
    return this.http
      .get<{role: string}>(`${environment.loginUrl}/api/user/role/${userId}`)
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
    console.log(key);
    const id = token.key.slice(0, i);
    console.log(token.key, "key," , id);
    return [key, +id];
  }

  private translateRole(role: string): string {
    return role.substr(4);
  }
}

