import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import {ApiService} from '../api.service'

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
@Component({
  selector: 'app-add-location-page',
  templateUrl: './add-location-page.component.html',
  styleUrls: ['./add-location-page.component.scss']
})
export class AddLocationPageComponent implements OnInit {
  addLocationForm:FormGroup;
  name = '';
  addressLine = '';
  city = '';
  state = '';
  zipcode = '';
  contactNumber = '';
  vehicleCapacity = '';

  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();


  constructor(private router: Router, private formBuilder: FormBuilder,private api: ApiService) { }

  ngOnInit(): void {
    this.addLocationForm = this.formBuilder.group({
      name : [null, [Validators.required,Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      addressLine : [null, [Validators.required,Validators.pattern("^(?:[A-Za-z0-9]+)(?:[A-Za-z0-9 _]*)$")]],
      city : [null, [Validators.required, Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      state : [null, [Validators.required,Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      zipcode : [null, [Validators.required,Validators.pattern("^[0-9]+$"),Validators.maxLength(5)]],
      contactNumber : [null, [Validators.required, Validators.pattern("^[0-9]+$"),Validators.maxLength(10)]],
      vehicleCapacity : [null, [Validators.required,Validators.pattern("^[0-9]+$")]]

    });
  }

  onFormSubmit() {
    this.isLoadingResults = true;
    //this.router.navigate(['/login']);

    var formData = {}

    formData['contactNumber'] = this.addLocationForm.get('contactNumber').value
    formData['name'] = this.addLocationForm.get('name').value
    formData['vehicleCapacity'] = this.addLocationForm.get('vehicleCapacity').value
    formData['street'] = this.addLocationForm.get('addressLine').value
    formData['city'] = this.addLocationForm.get('city').value
    formData['state'] = this.addLocationForm.get('state').value
    formData['zipcode'] = this.addLocationForm.get('zipcode').value

    this.api.addLocationAPI(formData).subscribe((res:any)=>{
      this.router.navigate(['/locations']);
    });

  }

}
