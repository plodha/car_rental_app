import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

import {ApiService} from '../api.service'
import * as _moment from 'moment';


interface Month {
  value: number;
  label: string;
}


/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {

  profileForm: FormGroup;
  updateAddressForm : FormGroup;

  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();

  name = '';
  username = '';
  password = ''
  address = '';
  city = '';
  state = '';
  zipcode = '';
  email = '';
  licensenumber = '';
  licenseExpiryDate = '';
  membershipStartDate = '';
  membershipEndDate = '';
  addressId = 0;
  userId = 0;

  months: Month[] = [
   {value: 1, label: 'Jan'},{value: 2, label: 'Feb'},
   {value: 3, label: 'Mar'},{value: 4, label: 'Apr'},
   {value: 5, label: 'May'},{value: 6, label: 'Jun'},
   {value: 7, label: 'Jul'},{value: 8, label: 'Aug'},
   {value: 9, label: 'Sep'},{value: 10, label: 'Oct'},
   {value: 11, label: 'Nov'},{value: 12, label: 'Dec'}
];
years:string[] = ['2020','2021','2022','2023','2024','2025','2026','2027','2028','2029','2030']

  constructor(private router: Router, private formBuilder: FormBuilder, private api:ApiService) { }

  ngOnInit(): void {
    this.profileForm = this.formBuilder.group({
        id : [null, Validators.required],
      firstName : [null, Validators.required],
      lastName :[null,Validators.required],
      username : [null, Validators.required],
      password : [null, Validators.required],
      address : [null, Validators.required],
      city : [null, Validators.required],
      state : [null, Validators.required],
      zipcode : [null, Validators.required],
      email : [null,  Validators.email],
      licensenumber : [null, Validators.required],
      licenseExpiryDate : [null, Validators.required],
      addressId: [null],
      userId:[null],
      creditCard:[null,[Validators.required,Validators.pattern("^[0-9]+$")]],
      creditCardExpMonth:[null,Validators.required],
      creditCardExpYear:[null,Validators.required],
      cvv:[null,[Validators.required,Validators.pattern("^[0-9]+$"),Validators.maxLength(3)]],
      membershipStartDate :[null,Validators.required],
      membershipEndDate: [null,Validators.required]

    });





    console.log('username');
    console.log(localStorage.username != undefined)
    if(localStorage.username != undefined ) {
      document.getElementById('profileName').innerHTML += localStorage.username
    }
    else {
      this.router.navigate(['/login']);

    }
    this.getProfileInformation(localStorage.id);



  }

  getProfileInformation(id: any) {
    var object = this;
   this.api.getCustomerById(id).subscribe((data: any) => {

     console.log(data)
     console.log(data.address.street)
     this.profileForm = this.formBuilder.group({
       id : data.id,
       firstName : data.firstName,
       lastName : data.lastName,
       username : data.userId.username,
       password : data.userId.password,
       address : data.address.street,
       city : data.address.city,
       state : data.address.state,
       zipcode : data.address.zipCode,
       email : data.email,
       licensenumber : data.licenseNumber,
       licenseExpiryDate : new FormControl(new Date(data.licenseExpDate+' 09:00')),
       addressId: data.address.id,
       userId: data.userId.id,
       creditCard : data.creditCard,
       creditCardExpMonth : this.splitMonth(data.creditCardExpDate),
       creditCardExpYear:this.splitYear(data.creditCardExpDate),
       cvv : data.cvv,
       membershipStartDate : data.membershipStartDate,
       membershipEndDate: data.membershipEndDate



     });

   });
 }

getCorrectDate(value){
  var splitted = value.split("-", 3);
  console.log(parseInt(splitted[0]))
  //return new FormControl(_moment([parseInt(splitted[0]), parseInt(splitted[1])-1, parseInt(splitted[0])]));
  return new FormControl(_moment([2017, 0, 1]));
}

splitMonth(str:any){
  return parseInt(str.split("-")[1]);
}
splitYear(str:any){
  return str.split("-")[0];
}

getCardExpirationDate(year:any,month:any) {

  var monthStr = ''
  var date = '01'
  monthStr = month.toString();
  if(month < 10) {
    monthStr = '0'+month.toString();
  }

  var str = ''+year+'-'+monthStr+'-'+date;
  return str
}
/*
{
    "creditCard": "35485739857",
    "creditCardExpDate": "2022-05-20",
    "id": 2,
    "userId": {
        "id": 3,
        "password": "notsecure",
        "username": "wqureshi5"
    },
    "firstName": "Wasae",
    "lastName": "Qureshi",
    "cvv": "454",
    "licenseNumber": "747324",
    "licenseExpDate": "1994-05-22",
    "membershipStartDate": "2020-05-03",
    "membershipEndDate": "2020-11-03",
    "email": "different5@gmail.com",
    "address": {
        "state": "ca",
        "id": 5,
        "street": "random street",
        "zipCode": 94086,
        "city": "random city"
    }
}
*/


  onFormSubmit() {
    this.isLoadingResults = true;
    //this.router.navigate(['/login']);

    var formData = {}
    var address = {}
    var user = {}

    formData['id'] = this.profileForm.get('id').value
    user['username'] = this.profileForm.get('username').value
    user['password'] = this.profileForm.get('password').value
    user['id'] = parseInt(this.profileForm.get('userId').value)
    formData['userId'] = user
    address['state'] = this.profileForm.get('state').value
    address['city'] = this.profileForm.get('city').value
    address['street'] = this.profileForm.get('address').value
    address['zipCode'] = parseInt(this.profileForm.get('zipcode').value)
    address['id'] = parseInt(this.profileForm.get('addressId').value);

    //formData['id'] = this.profileForm.get('id').value
    formData['firstName'] = this.profileForm.get('firstName').value
    formData['lastName'] = this.profileForm.get('lastName').value
    formData['licenseNumber'] = this.profileForm.get('licensenumber').value
    formData['licenseExpDate'] = this.convertDateString(this.profileForm.get('licenseExpiryDate').value)
    formData['email'] = this.profileForm.get('email').value
    formData['creditCard'] = this.profileForm.get('creditCard').value
    formData['creditCardExpDate'] = this.getCardExpirationDate(this.profileForm.get('creditCardExpYear').value,
                                  this.profileForm.get('creditCardExpMonth').value);
    formData['cvv'] = this.profileForm.get('cvv').value

    formData['address'] = address


    formData["membershipStartDate"] =  this.profileForm.get('membershipStartDate').value
    formData["membershipEndDate"]= this.profileForm.get('membershipEndDate').value

      console.log(formData)

    this.api.updateCustomerAPI(formData).subscribe((res:any)=>{
      this.isLoadingResults = false;
       this.router.navigate(['/customerPage']);
    });
    // this.api.getCustomerById(this.casesForm.value)
    //   .subscribe((res: any) => {
    //       const id = res._id;
    //       this.isLoadingResults = false;
    //       this.router.navigate(['/login']);
    //     }, (err: any) => {
    //       console.log(err);
    //       this.isLoadingResults = false;
    //     });
  }
  convertDateString(dateString:any) {
    var d = new Date(dateString);
    var year = d.getFullYear();
    var month = d.getMonth()+1;
    var date = d.getDate();
    var monthStr = ''+month.toString();
    var dateStr = ''+date.toString()


    if(month < 10) {
      monthStr = '0'+month.toString();
    }
    if(date < 10){
      dateStr = '0'+date.toString();
    }
    var str = ''+year+'-'+monthStr+'-'+dateStr;
    return str
  }
}
