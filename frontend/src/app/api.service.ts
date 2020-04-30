import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  api_url = "https://api.wasaequreshi.com/"

  constructor(private http: HttpClient) { 

  }

  registrationAPI(username, password, firstName, lastName, licenseNumber, licenseExpDate, email, street, city, state, zipcode) {
    
    var results;
    var end_point = "registration";
    
    var params = new HttpParams()
      .set('username', username)
      .set('password', password)
      .set('firstName', firstName)
      .set('lastName', lastName)
      .set('licenseNumber', licenseNumber)
      .set('licenseExpDate', licenseExpDate)
      .set('email', email)
      .set('street', street)
      .set('city', city)
      .set('state', state)
      .set('zipcode', zipcode);
    
    this.http.post(this.api_url + end_point, params).subscribe(res => {results = res});

    return results;

  }
}
