import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'translateFuel'
})
export class TranslateFuelPipe implements PipeTransform {

  transform(value: string): string {
    switch (value) {
      case 'PETROL': {
        return 'benzyna';
      }
      case 'DIESEL': {
        return 'diesel';
      }
      case 'GAS': {
        return 'gaz';
      }
    }
    return value;
  }

}
