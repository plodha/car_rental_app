import { Injectable } from '@angular/core';
import {HttpClient, HttpParams,HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  //api_url = "https://api.wasaequreshi.com/"
  api_url = "http://localhost:8020/"

  location_endpoint = "location"

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

public getAllLocations(){
    let headers = { 'Content-Type': 'application/json','Accept':'application/json',
               'Access-Control-Allow-Origin': '*' };
    return this.http.get(this.api_url + this.location_endpoint,{headers})


}
  /*
      regFormGroup format:
          {
            "address": {
                "state": "ca",
                "city": "random city",
                "street": "random street",
                "zipCode": 94086
            },
            "userId": {
                "password": "password",
                "username": "jeyasriCustomer"
            },
            "firstName": "Jeyasri",
            "lastName": "Customer",
            "licenseNumber": "747324",
            "licenseExpDate": "1994-05-22",
            "membershipStartDate": "2020-05-01",
            "membershipEndDate": "2021-05-01",
            "email": "jeyasriCustomer@gmail.com",
            "creditCard" : "35485739857",
            "creditCardExpDate" : "2022-05-20",
            "cvv" : "454"
          
          }
    /registration API return:
        Success:
          {"role":"Customer","id":"4","status":"200","username":"WasaeCustomer"}
        Error:
          {"message":"<some reason it failed>","status":"400"}
  */
    regAPI(regFormGroup) {
      let headers = { 'Content-Type': 'application/json','Accept':'application/json', "Access-Control-Allow-Origin": "*"};
      let body = JSON.stringify(regFormGroup.value);
      return this.http.post("https://api.wasaequreshi.com/registration", body, {headers});
    }

}
