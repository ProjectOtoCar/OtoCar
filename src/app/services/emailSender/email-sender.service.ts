import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Contact } from 'src/app/interfaces/Contact';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmailSenderService {

  constructor(private http: HttpClient) { }

  sendEmail(contact: Contact): Observable<any> {
    return this.http.post<Contact>(`${environment.basicUrl}/api/mail/send`, contact);
  }
}
