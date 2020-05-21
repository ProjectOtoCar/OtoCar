import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginUser } from 'src/app/interfaces/loginUser.model';
import { HttpClient } from '@angular/common/http';
import { RegisterUser } from '../../interfaces/RegisterUser.model';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { Token } from 'src/app/interfaces/Token.modal';
import { UserPageService } from '../user-page.service';
import { UserCreated } from 'src/app/interfaces/UserCreated.model';
import { SellerPost } from 'src/app/interfaces/SellerPost.model';

@Injectable({
  providedIn: 'root'
})
export class LoginUserService {

  loginUser = new BehaviorSubject(null);
  token = new BehaviorSubject(null);
  constructor(private http: HttpClient, private userPageService: UserPageService) { }

  signIn(login): Observable<Token> {
    return this.http.post(`${environment.loginUrl}/api/user`, login)
    .pipe(map((token: Token) => {
      let i = 0;
      while (token.key[i] === ' ') {
        i++;
      }
      const id = token.key.slice(0, i);
      console.log(token.key, id);
      this.token.next(token.key);
      return token;
    }));
    //  this.loginUser.next({id: 6, role: 'USER', email: 'lala@o2.pl'} as LoginUser);
  }

  signOut(): void {
    this.loginUser.next(null);
    localStorage.removeItem('test');
  }

  autoSignIn(): void {
    this.loginUser.next({id: 6, role: 'ADMIN', email: 'lala@o2.pl'} as LoginUser);
    localStorage.setItem('test', 'test');
  }

  createUser(sellerPost: SellerPost): Observable<any> {
    sellerPost.registerUser.role = 'ROLE_USER';
    console.log(sellerPost);
    return this.http.post(`${environment.loginUrl}/api/user/regi`, sellerPost.registerUser);
  }
}

