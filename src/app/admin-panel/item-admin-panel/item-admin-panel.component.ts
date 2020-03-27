import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-item-admin-panel',
  templateUrl: './item-admin-panel.component.html',
  styleUrls: ['./item-admin-panel.component.scss']
})
export class ItemAdminPanelComponent implements OnInit {

  @Input()
  data;

  constructor() { }

  ngOnInit(): void {
  }

}
