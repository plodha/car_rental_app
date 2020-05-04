import { Injectable } from '@angular/core';
import {HttpClient, HttpParams,HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  api_url = "https://api.wasaequreshi.com/"
  //api_url = "http://localhost:8020/"

  location_endpoint = "location"
  customer_endpoint = "getAllCustomers"
  vehicle_endpoint = 'vehicle'
  vehicleTypes_endpoint = "getAllVehicleTypes"
  damageType_endpoint = "getDamageForVehicleType"
  reservation_endpoint = 'reservation'
  priceInfo_endpoint = "getPriceforVehicleType"




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

public getAllLocations(){
    let headers = { 'Content-Type': 'application/json','Accept':'application/json',
               'Access-Control-Allow-Origin': '*' };
    return this.http.get(this.api_url + this.location_endpoint,{headers})


}

public getAllCustomers(){
    let headers = { 'Content-Type': 'application/json','Accept':'application/json',
               'Access-Control-Allow-Origin': '*' };
    return this.http.get(this.api_url + this.customer_endpoint,{headers})

}

public getAllVehicles(){
    let headers = { 'Content-Type': 'application/json','Accept':'application/json',
               'Access-Control-Allow-Origin': '*' };
    return this.http.get(this.api_url + this.vehicle_endpoint,{headers})

}

public getAllVehicleTypes(){
    let headers = { 'Content-Type': 'application/json','Accept':'application/json',
               'Access-Control-Allow-Origin': '*' };
    return this.http.get(this.api_url + this.vehicleTypes_endpoint,{headers})

}

public getAllDamageType(vehicleType:any){
    let headers = { 'Content-Type': 'application/json','Accept':'application/json',
               'Access-Control-Allow-Origin': '*' };
    return this.http.get(this.api_url + this.damageType_endpoint+"/?vehicleTypeId="+vehicleType,{headers})

}
public getAllReservationsForUser(user_id:any){
    let headers = { 'Content-Type': 'application/json','Accept':'application/json',
               'Access-Control-Allow-Origin': '*' };
    return this.http.get(this.api_url + this.reservation_endpoint+"/"+user_id,{headers})

}
public getPriceInfo(vehicleTypeId:any){
    let headers = { 'Content-Type': 'application/json','Accept':'application/json',
               'Access-Control-Allow-Origin': '*' };
    return this.http.get(this.api_url + this.priceInfo_endpoint+"/?vehicleTypeId="+vehicleTypeId,{headers})

}



}
