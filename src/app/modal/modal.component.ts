import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent implements OnInit {
  @Input() title: string;
  @Input() message: string;
  @Input() firstAction: string;
  @Input() secondAction: string;
  @Input() showAcceptButton = false;
  @Input() isLoading = false;
  @Input() isError = false;
  @Input() errorMessage = 'Coś poszło nie tak. Prosimy spróbować ponownie';
  @Output() action = new EventEmitter<boolean>();
  constructor() { }

  ngOnInit(): void {
  }

  onAction(action: boolean): void {
    this.action.emit(action);
  }

}
