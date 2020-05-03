import { Injectable } from '@angular/core';
import {HttpClient, HttpParams,HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  //api_url = "https://api.wasaequreshi.com/"
  api_url = "http://localhost:8020/"
  constructor(private http: HttpClient) {

  }

  public callLogin(formObj: any) {
    var end_point = "authNew"


    let headers: any = new HttpHeaders({ 'Content-Type': 'application/json','Accept':'application/json',
               'Access-Control-Allow-Origin': '*' });
      console.log(params)

      let username = (formObj.get('username').value);
      let password = (formObj.get('password').value);
   //const options = { params: new HttpParams({fromString: "_page=1&_limit=20"}) };
   var params = '?username='+username+'&password='+password;

    return this.http.get(this.api_url + end_point+params)
  }
  registerUserAPI(formObj: any)
  {
    const formData = new FormData();
    formData.append('file', formObj.get('file').value);
    formData.append('bgfile', formObj.get('bgfile').value);






  }

  registrationAPI(username, password, firstName, lastName, licenseNumber, licenseExpDate, email, street, city, state, zipcode) {

    var end_point = "register";

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



    return this.http.post(this.api_url + end_point, params);

  }
}
