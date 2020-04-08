import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Contact } from '../interfaces/Contact';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ContactFormService {

  constructor(private http: HttpClient) { }
  sendEmail(contact: Contact) {
    return this.http.post<Contact>(`${environment.basicUrl}/api/mail/send`, contact);
  }
}
