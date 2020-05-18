import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginUserService } from '../services/loginUser/login-user.service';
import { map } from 'rxjs/operators';
import { LoginUser } from '../interfaces/loginUser.model';

@Injectable({
    providedIn: 'root'
})
export class AuthAdminGuard implements CanActivate {
    constructor(private loginUserService: LoginUserService, private router: Router) {}
    canActivate(route: ActivatedRouteSnapshot, router: RouterStateSnapshot):
     boolean | UrlTree | Promise<boolean | UrlTree> | Observable<boolean | UrlTree>  {
        return this.loginUserService.loginUser.pipe(map((loginUser: LoginUser) => {
            if (loginUser !== null && loginUser !== undefined) {
                if (loginUser.role === 'ADMIN') {
                    return true;
                }
            }
            return this.router.createUrlTree(['/']);
        }));
    }
}
