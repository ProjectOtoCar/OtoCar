import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateFuelPipe } from './translate/translate-fuel.pipe';
import { ShorterTextPipe } from './shorter-text/shorter-text.pipe';
import { DateFormatPipe } from './dateFormat/date-format.pipe';
import { UpperCaseFirstLetterPipe } from './upperCaseFirstLetter/upper-case-first-letter.pipe';



@NgModule({
  declarations: [
    TranslateFuelPipe,
    ShorterTextPipe,
    DateFormatPipe,
    UpperCaseFirstLetterPipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    TranslateFuelPipe,
    ShorterTextPipe,
    DateFormatPipe,
    UpperCaseFirstLetterPipe
  ],
})
export class PipeModule { }
