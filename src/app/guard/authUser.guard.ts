import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, UrlTree } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginUserService } from '../services/loginUser/login-user.service';
import { map } from 'rxjs/operators';
import { LoginUser } from '../interfaces/loginUser.model';

@Injectable({
    providedIn: 'root'
})
export class AuthUserGuard implements CanActivate {
    constructor(private loginUserService: LoginUserService, private router: Router) {}
    canActivate(route: ActivatedRouteSnapshot, router: RouterStateSnapshot):
    boolean | UrlTree | Promise<boolean | UrlTree> | Observable<boolean | UrlTree> {
        const id = route.queryParams.userId;
        return this.loginUserService.loginUser.pipe(map((loginUser: LoginUser) => {
            if (loginUser !== null && loginUser !== undefined) {
                if (+loginUser.id === +id) {
                    return true;
                } else if (loginUser.role === 'ADMIN' || loginUser.role === 'MODERATOR' ) {
                    return true;
                } else {
                    return this.router.createUrlTree(['/']);
                }
            }
            return this.router.createUrlTree(['/sign-in']);
        }));
    }
}
