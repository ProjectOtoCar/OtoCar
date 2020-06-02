import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { LoginUserService } from '../services/loginUser/login-user.service';
import { map } from 'rxjs/operators';

export class CustomAsyncValidators {
    static isUsernameExisted(loginUserService: LoginUserService): (control: FormControl) => Observable<{[key: string]: boolean}> {
        return (control: FormControl): Observable<{[key: string]: boolean}> => {
            const username: string = control.value;
            return loginUserService.isUsernameExisted(username)
            .pipe(map((isExisted: boolean) => {
                console.log(isExisted);
                if (isExisted) {
                    return {isusernameexisted: true};
                }
                return null;
            }));
        };
    }
}
