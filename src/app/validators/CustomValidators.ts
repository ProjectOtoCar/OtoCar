import { FormControl } from '@angular/forms';

export class CustomValidators {
    static requiredCapitalLetter(control: FormControl): {[key: string]: boolean} {
        const password: string = control.value;
        if (password?.toLowerCase() === password) {
            return {requiredcapitalletter: true};
        }
        return null;
    }

    static requiredSpecialSign(control: FormControl): {[key: string]: boolean} {
        const password: string = control.value;
        if (!password?.split('').some((x) =>
        (x >= '!' && x <= '/') ||
        (x >= ':' && x <= '@') ||
        (x >= '{' && x <= '-')
        )) {
            return {requiredspecialsign: true};
        }
        return null;
    }

    static requiredDigit(control: FormControl): {[key: string]: boolean} {
        const password: string = control.value;
        if (!password?.split('').some((x) => x >= '0' && x <= '9')) {
            return {requireddigit: true};
        }
        return null;
    }

    static withoutSpace(control: FormControl): {[key: string]: boolean} {
        const password: string = control.value;
        if (password?.indexOf(' ') !== -1){
            return {withoutspace: true};
        }
        return null;
    }
}
