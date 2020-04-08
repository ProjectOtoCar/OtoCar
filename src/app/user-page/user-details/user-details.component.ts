import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Event } from '@angular/router';
import { UserPageService } from 'src/app/services/user-page.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {

  queryParams;
  isFoundUser: boolean;
  constructor(
    private activatedRoute: ActivatedRoute,
    private userPageService: UserPageService ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      this.queryParams = params;
    })
    this.userPageService.isUserFound.subscribe(
      (isFound: boolean) => {
        this.isFoundUser = isFound;
      }
    );
  }

}
