import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Addvertisment } from 'src/app/interfaces/Addvertisment';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AddvertismentService {

  constructor(private http: HttpClient) { }

  postAddvertisment(add: Addvertisment): Observable<Addvertisment> {
    return this.http.post<Addvertisment>(`${environment.basicUrl}/api/advertisement`, add);
  }
}
