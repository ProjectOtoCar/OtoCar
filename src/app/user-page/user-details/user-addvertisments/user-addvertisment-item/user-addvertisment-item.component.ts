import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ShortAddvertisment } from 'src/app/interfaces/ShortAddvertisment.model';
import { AddvertismentService } from 'src/app/services/addvertisment/addvertisment.service';

@Component({
  selector: 'app-user-addvertisment-item',
  templateUrl: './user-addvertisment-item.component.html',
  styleUrls: ['./user-addvertisment-item.component.scss']
})
export class UserAddvertismentItemComponent implements OnInit {

  @Input() shortAdvertisment: ShortAddvertisment;
  @Output() deleteEmitter = new EventEmitter<number>();

  isDeleteLoading = false;
  isError = false;
  isToogleActiveLoading = false;

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
    this.isToogleActiveLoading = true;
    this.isError = false;
    this.addvertismentService
    .patchAddvertisment(
      this.shortAdvertisment.id,
      {
        active: !this.shortAdvertisment.active
      }).subscribe(() => {
        this.isToogleActiveLoading = false;
        this.isError = false;
        this.shortAdvertisment.active = !this.shortAdvertisment.active;
      }, error => {
        this.isToogleActiveLoading = false;
        this.isError = true;
      });
  }
  getUrl(): string {
    return 'url(' + this.shortAdvertisment.images[0]?.photo + ')';
  }

}
