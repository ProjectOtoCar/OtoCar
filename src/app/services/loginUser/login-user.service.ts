import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginUser } from 'src/app/interfaces/loginUser.model';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { Token } from 'src/app/interfaces/Token.modal';
import { SellerPost } from 'src/app/interfaces/SellerPost.model';
import { Seller } from 'src/app/interfaces/Seller';
import { AppUser } from 'src/app/interfaces/AppUser.model';

@Injectable({
  providedIn: 'root'
})
export class LoginUserService {

  loginUser = new BehaviorSubject(null);
  token = new BehaviorSubject(null);
  constructor(private http: HttpClient) { }

  signIn(login): Observable<Observable<any>> {
    return this.http.post(`${environment.loginUrl}/api/user`, login)
    .pipe(map((token: Token): Observable<any> => {
      if (!token || token.key.length < 10) {
        return null;
      }
      const [key, authId] = this.getIdAndKey(token);
      return this.getSellerByUserId(authId)
        .pipe(map((seller: Seller): any => {
            return this.getAppUser(authId)
              .pipe(map((appUser: AppUser): any => {
                if (appUser.enabled) {
                    const loginUser = {id: seller.id, role: appUser.role, email: appUser.username} as LoginUser;
                    localStorage.setItem('user', JSON.stringify(loginUser));
                    this.loginUser.next(loginUser);
                    this.token.next(key);
                    localStorage.setItem('token', key);
                    return true;
                  } else {
                    return false;
                  }
              }));
        }));
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

  isUsernameExisted(username: string): Observable<any> {
    return this.http
    .get(`${environment.loginUrl}/api/user/username/${username}`)
    .pipe(map((response: {isExisted: boolean}) => response.isExisted));
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

  getAppUser(authId: number): Observable<AppUser> {
    return this.http
      .get<AppUser>(`${environment.loginUrl}/api/user/${authId}`);
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

