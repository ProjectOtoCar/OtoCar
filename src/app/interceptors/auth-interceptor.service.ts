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
            exhaustMap(token => {
                console.log(token);
                if (token === null) {
                    return next.handle(req);
                }
                const modifiedReq = req.clone({
                    params: new HttpParams()
                        .set('Authorization', 'Bearer ' + token.Key)
                });
                return next.handle(modifiedReq);
            }))
    }
}
