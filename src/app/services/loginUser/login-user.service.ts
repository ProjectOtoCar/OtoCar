import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginUser } from 'src/app/interfaces/loginUser.model';
import { HttpClient } from '@angular/common/http';
import { RegisterUser } from '../../interfaces/RegisterUser.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginUserService {

  loginUser = new BehaviorSubject(null);
  constructor(private http: HttpClient) { }

  signIn(login): void {
     this.loginUser.next({id: 6, role: 'USER', email: 'lala@o2.pl'} as LoginUser);
  }

  signOut(): void {
    this.loginUser.next(null);
    localStorage.removeItem('test');
  }

  autoSignIn(): void {
    this.loginUser.next({id: 6, role: 'USER', email: 'lala@o2.pl'} as LoginUser);
    localStorage.setItem('test', 'test');
  }

  createUser(registerUser: RegisterUser): Observable<any> {
    registerUser.role = 'ROLE_USER';
    console.log(registerUser);
    return this.http.post(`${environment.loginUrl}/api/user/regi`, registerUser);
  }
}
