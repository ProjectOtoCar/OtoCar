import {Component, Input, OnInit} from '@angular/core';
import { AdminPanelService } from '../services/admin-panel.service';
import { Seller } from '../interfaces/Seller';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent implements OnInit {

  datas: Seller[];
  currentData: number;
  isLoading = true;
  @Input()
  data;

  constructor(private adminPanelService: AdminPanelService) { }

  ngOnInit(): void {
    this.isLoading = true;
    this.adminPanelService.getSellers(1).subscribe(response => {
      this.datas = response;
      this.currentData = Date.now();
      this.isLoading = false;
    });
  }

}
