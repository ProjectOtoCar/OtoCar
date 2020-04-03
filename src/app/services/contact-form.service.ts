import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Contact } from '../interfaces/Contact';

@Injectable({
  providedIn: 'root'
})
export class ContactFormService {

  constructor(private http: HttpClient) { }
  sendEmail(contact: Contact) {
    return this.http.post<Contact>('http://localhost:8080/api/mail/send', contact);
  }
}
