import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { LoginUser } from 'src/app/interfaces/loginUser.model';

@Injectable({
  providedIn: 'root'
})
export class LoginUserService {

  loginSelerId = new BehaviorSubject(null);
  constructor() { }

  signIn(): void {
    this.loginSelerId.next({id: 6, role: 'USER'} as LoginUser);
  }

  signOut(): void {
    this.loginSelerId.next(null);
  }

}
