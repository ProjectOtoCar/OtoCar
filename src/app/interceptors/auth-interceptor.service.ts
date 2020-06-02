import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpParams } from '@angular/common/http';
import { LoginUserService } from '../services/loginUser/login-user.service';
import { take, exhaustMap } from 'rxjs/operators';


@Injectable()
export class AuthInterceptorService implements HttpInterceptor {
    constructor(private loginUserService: LoginUserService) {}
    intercept(req: HttpRequest<any>, next: HttpHandler) {
       return this.loginUserService.token.pipe(
            take(1),
            exhaustMap((token: string) => {
                if (token === null) {
                    return next.handle(req);
                }
                const modifiedReq = req.clone({
                    headers: req.headers
                        .set('Authorization', 'Bearer ' + token)
                });
                return next.handle(modifiedReq);
            }));
    }
}
