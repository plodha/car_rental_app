import { Component, OnInit } from '@angular/core';
//import { ApiService } from '../api.service';
import { ErrorStateMatcher } from '@angular/material/core';



import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import {ActivatedRoute, Router ,NavigationEnd} from '@angular/router';
import {ApiService} from '../api.service'
import {Observable} from 'rxjs/Rx';
import { switchMap } from 'rxjs/operators';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
@Component({
  selector: 'app-dropoff-survey-page',
  templateUrl: './dropoff-survey-page.component.html',
  styleUrls: ['./dropoff-survey-page.component.scss']
})
export class DropoffSurveyPageComponent implements OnInit {

  //surveyForm : FormGroup;

  isLoadingResults = false;

  public physicalDamage = "0";
  public scratches =  "0";
  public headlightDamage =  "0";
  public sideMirrorDamage = "0";
  public glassDamage ="0";
  public flatTyre = "0";
  constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,private api: ApiService) {


    var OBJ = this;
    this.actr.data.subscribe((data) => {
       console.log(data);
       var damages = data.damages;
     });
}

  ngOnInit(): void {
    /*this.surveyForm = this.formBuilder.group({
      //
      //flatTyre : [null, Validators.required],
      physicalDamage : [null,Validators.required],
      headlightDamage :[null,Validators.required],
      glassDamage :[null,Validators.required],
      sideMirrorDamage :[null,Validators.required],
      scratches : [null,Validators.required],


    });*/

  }

  validateForm(){
  }

  onFormSubmit() {
    this.isLoadingResults = true;
    var queryString = '';
    console.log(this.flatTyre);

    console.log(this.scratches)
    if(this.flatTyre == "1"){
      if(queryString == ''){
        queryString+= '1'
      }
    }
    if(this.physicalDamage == "1"){
      if(queryString == ''){
        queryString += '2'
      }
      else{
        queryString += ',2'
      }
    }
    if(this.headlightDamage == "1"){
      if(queryString == ''){
        queryString += '3'
      }
      else{
        queryString += ',3'
      }
    }

    if(this.sideMirrorDamage == "1"){
      if(queryString == ''){
        queryString += '4'
      }
      else{
        queryString += ',4'
      }
    }
    if(this.glassDamage == "1"){
      if(queryString == ''){
        queryString += '5'
      }
      else{
        queryString += ',5'
      }
    }
    if(this.scratches == "1"){
      if(queryString == ''){
        queryString += '6'
      }
      else{
        queryString += ',6'
      }
    }

    console.log(queryString)
    console.log('dropoff')
    console.log(this.getDropoffDateString())
    console.log('id')
    console.log(this.actr)
    var formData = {}
    if(queryString == ''){
      formData['IsDamage'] = false
      queryString = '0'
    }
    else{
      formData['IsDamage'] = true
    }
    formData['damageId'] = queryString
    formData['actualDropOffTime'] = this.getDropoffDateString();
    var OBJ = this;
     this.actr.params.subscribe(params => {
      console.log(params) //log the entire params object
      console.log(params['id']) //log the value of id
      var reservationId = params['id']
      formData['reservationId'] = reservationId;
      this.api.computeInvoiceAPI(formData).subscribe((res:any) => {
          OBJ.isLoadingResults = false;
          console.log(res)
            //alert("Thank you for your business! Please view the final invoice in reservations ")
            this.router.navigate(['/invoice/'+res]);

      })

    });
  //  console.log(this.router.actr.paramMap.get('id'));

    //http://localhost:8080/computeInvoice/1,2,3?actualDropOffTime=1/15/2020 3:57 PM&reservationId=1&IsDamage=true
//https://api.wasaequreshi.com/computeInvoice/3?actualDropOffTime5/6/2020%209:34&reservationId=10&IsDamage=true

    //this.router.navigate(['/login']);
  }
  getDropoffDateString(){

    var pickupdate = new Date();
    var pickupdatestring = ''+(pickupdate.getMonth()+1);
    var min = pickupdate.getMinutes();
    var minStr = pickupdate.getMinutes().toString()
    if(min <10){
      minStr = '0'+ pickupdate.getMinutes().toString()
    }
    pickupdatestring += "/"+pickupdate.getDate()+"/"+pickupdate.getFullYear()+" "+pickupdate.getHours()+":"+minStr;
    return pickupdatestring;
  }
}
