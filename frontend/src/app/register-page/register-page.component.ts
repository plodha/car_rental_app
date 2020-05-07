import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

import {ApiService} from '../api.service'

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

interface Month {
  value: number;
  label: string;
}


@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {
  registerForm: FormGroup;
  firstName = '';
  lastName = '';
  dateofbirth = '';
  address = '';
  city = '';
  state = '';
  zipcode = '';
  email = '';
  licensenumber = '';
  licenseExpiryDate = new Date(2020, 5, 1);
  creditCardExpYear ='';
  creditCardExpMonth = '';
  creditCard = '';
  cvv = '';

  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();
  startDate = new Date(1940, 0, 1);

  months: Month[] = [
   {value: 1, label: 'Jan'},{value: 2, label: 'Feb'},
   {value: 3, label: 'Mar'},{value: 4, label: 'Apr'},
   {value: 5, label: 'May'},{value: 6, label: 'Jun'},
   {value: 7, label: 'Jul'},{value: 8, label: 'Aug'},
   {value: 9, label: 'Sep'},{value: 10, label: 'Oct'},
   {value: 11, label: 'Nov'},{value: 12, label: 'Dec'}
];
years:string[] = ['2020','2021','2022','2023','2024','2025','2026','2027','2028','2029','2030']

  constructor(private router: Router, private formBuilder: FormBuilder,private api:ApiService) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName : [null, Validators.required],
      lastName : [null, Validators.required],
      username : [null, Validators.required],
      password:[null, Validators.required],
      dateofbirth : [null, Validators.required],
      address : [null, Validators.required],
      city : [null, Validators.required],
      state : [null, Validators.required],
      zipcode : [null, Validators.required],
      email : [null,  Validators.email],
      licensenumber : [null, Validators.required],
      licenseExpiryDate : [null, Validators.required],
      creditCard:[null,[Validators.required,Validators.pattern("^[0-9]+$")]],
      creditCardExpMonth:[null,Validators.required],
      creditCardExpYear:[null,Validators.required],
      cvv:[null,[Validators.required,Validators.pattern("^[0-9]+$"),Validators.maxLength(3)]]

    });
  }




  onFormSubmit() {
    this.isLoadingResults = true;

    var formData = {}
    var address = {}
    var user = {}

    user['username'] = this.registerForm.get('username').value
    user['password'] = this.registerForm.get('password').value
    formData['userId'] = user
    address['state'] = this.registerForm.get('state').value
    address['city'] = this.registerForm.get('city').value
    address['street'] = this.registerForm.get('address').value
    address['zipCode'] = parseInt(this.registerForm.get('zipcode').value)
    //formData['id'] = this.registerForm.get('id').value
    formData['firstName'] = this.registerForm.get('firstName').value
    formData['lastName'] = this.registerForm.get('lastName').value
    formData['licenseNumber'] = this.registerForm.get('licensenumber').value
    formData['licenseExpDate'] = this.convertDateString(this.registerForm.get('licenseExpiryDate').value)
    formData['email'] = this.registerForm.get('email').value
    formData['creditCard'] = this.registerForm.get('creditCard').value
    formData['creditCardExpDate'] = this.getCardExpirationDate(this.registerForm.get('creditCardExpYear').value,
                                  this.registerForm.get('creditCardExpMonth').value);
    formData['cvv'] = this.registerForm.get('cvv').value

    formData['address'] = address


    formData["membershipStartDate"] =  this.getCurrentDateString()
    formData["membershipEndDate"]= this.getExpirationDateString()

    console.log(formData)



  this.api.regAPI(formData).subscribe((res:any)=>{
    this.isLoadingResults = false;
    if (res.status == "200") {
      alert('Registration is successful ! Your membership fee of $100 is detected from your credit card. Your registration is active for 6 months')
      this.router.navigate(['/customerPage']);
    }
    if(res.status == "400"){
      document.getElementById('errorBlock').innerHTML = res.message;
  }

    //
  });
    /*
    this.api.addCases(this.casesForm.value)
      .subscribe((res: any) => {
          const id = res._id;
          this.isLoadingResults = false;
          this.router.navigate(['/login']);
        }, (err: any) => {
          console.log(err);
          this.isLoadingResults = false;
        });
        */
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

  getCurrentDateString(){
    //yyyy-mm-dd
    var monthStr = ''
    var dateStr = ''
    var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth()+1;
    var date = d.getDate();
    dateStr = ''+date.toString()
    monthStr = month.toString();

    if(month < 10) {
      monthStr = '0'+month.toString();
    }
    if(date < 10){
      dateStr = '0'+date.toString();
    }
    var str = ''+year+'-'+monthStr+'-'+dateStr;
    return str
  }
  getExpirationDateString(){
    var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth()+7;
    var date = d.getDate();
      var monthStr = ''
      var dateStr = ''
      dateStr = ''+date.toString()
      monthStr = month.toString();

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
