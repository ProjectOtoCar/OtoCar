import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateFormat'
})
export class DateFormatPipe implements PipeTransform {

  transform(value: Date): string {
    if (value === null) {
      return null;
    }
    console.log(value);
    const month = (value.getMonth() + 1) < 10 ? '0' + (value.getMonth() + 1) : (value.getMonth() + 1);
    const day = value.getDate() < 10 ? '0' + value.getDate() : value.getDate();
    return `${value.getFullYear()}-${month}-${day}`;
  }

}
