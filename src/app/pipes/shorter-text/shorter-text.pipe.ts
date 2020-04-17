import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'shorterText'
})
export class ShorterTextPipe implements PipeTransform {

  transform(value: string, numberOfLetters: number = 70): string {
    if (value.length <= numberOfLetters) {
      return value;
    }
    return value.slice(0, numberOfLetters) + '...';
  }

}
