import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SerachAdminFormService {

  constructor(private http: HttpClient) { }

  getAccountTypes(): Observable<[string]> {
    return this.http.get<[string]>(`${environment.basicUrl}/api/enums/account`);
  }

}
