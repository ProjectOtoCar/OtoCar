import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { LoginUser } from 'src/app/interfaces/loginUser.model';

@Injectable({
  providedIn: 'root'
})
export class LoginUserService {

  loginUser = new BehaviorSubject(null);
  constructor() { }

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

}
