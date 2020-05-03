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

  constructor(private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.profileForm = this.formBuilder.group({
      name : [null, Validators.required],
      username : [null, Validators.required],
      password : [null, Validators.required],


    });

    this.updateAddressForm = this.formBuilder.group({
      address : [null, Validators.required],
      city : [null, Validators.required],
      state : [null, Validators.required],
      zipcode : [null, Validators.required],
      email : [null,  Validators.email],
      licensenumber : [null, Validators.required],
      licenseExpiryDate : [null, Validators.required],
    });
  }

  onFormSubmit() {
    this.isLoadingResults = true;
    this.router.navigate(['/login']);
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

  onAddressSubmit() {
    this.isLoadingResults = true;
    this.router.navigate(['/login']);
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

}
