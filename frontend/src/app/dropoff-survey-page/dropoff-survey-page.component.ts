import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
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

  surveyForm : FormGroup;

  isLoadingResults = false;
  flatTyre = new FormControl('yes');
  physicalDamage = new FormControl('yes');
  scratches =  new FormControl('yes');
  headlightDamage =  new FormControl('yes');
  sideMirrorDamage = new FormControl('yes');
  glassDamage = new FormControl('yes');



  constructor(private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.surveyForm = this.formBuilder.group({
      //
      // flatTyre : ['', Validators.required],
      // physicalDamage : [null,Validators.required],
      // headlightDamage :[null,Validators.required],
      // glassDamage :[null,Validators.required],
      // sideMirrorDamage :[null,Validators.required],
      // scratches : [null,Validators.required],
      // gender : ['',Validators.required],

    });
  }

  onFormSubmit() {
    this.isLoadingResults = true;
    this.router.navigate(['/login']);
  }

}
