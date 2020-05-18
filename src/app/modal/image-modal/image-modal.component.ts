import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-image-modal',
  templateUrl: './image-modal.component.html',
  styleUrls: ['./image-modal.component.scss']
})
export class ImageModalComponent implements OnInit {
  @Input() image;
  @Output() closeModal = new EventEmitter<boolean>();
  constructor() { }

  ngOnInit(): void {
  }

  hideModal(): void {
    this.closeModal.emit(true);
  }
}
