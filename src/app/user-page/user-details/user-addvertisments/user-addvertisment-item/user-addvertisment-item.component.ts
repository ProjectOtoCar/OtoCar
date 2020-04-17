import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ShortAddvertisment } from 'src/app/interfaces/ShortAddvertisment.model';
import { AddvertismentService } from 'src/app/services/addvertisment/addvertisment.service';

@Component({
  selector: 'app-user-addvertisment-item',
  templateUrl: './user-addvertisment-item.component.html',
  styleUrls: ['./user-addvertisment-item.component.scss']
})
export class UserAddvertismentItemComponent implements OnInit {

  isDeleteLoading = false;
  isError = false;
  @Input() shortAdvertisment: ShortAddvertisment;
  @Output() deleteEmitter = new EventEmitter<number>();

  constructor(private addvertismentService: AddvertismentService) {
   }

  ngOnInit(): void {
  }

  showDetails(): void {

  }

  modifyAddvertisment(): void {

  }

  deleteAddvertisment(id: number): void {
    this.isDeleteLoading = true;
    this.isError = false;
    this.addvertismentService.deleteAddvertisment(id)
    .subscribe(() => {
       this.isDeleteLoading = false;
       this.isError = false;
       this.deleteEmitter.emit(id);
    }, error => {
      this.isDeleteLoading = false;
      this.isError = true;
    });
  }

  toggleActive(): void {

  }
  getUrl(): string {
    return 'url(' + this.shortAdvertisment.images[0]?.photo + ')';
  }

}
