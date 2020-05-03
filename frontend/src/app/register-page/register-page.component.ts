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
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {
  registerForm: FormGroup;
  name = '';
  dateofbirth = '';
  address = '';
  city = '';
  state = '';
  zipcode = '';
  email = '';
  licensenumber = '';
  licenseExpiryDate = '';

  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();
  startDate = new Date(1940, 0, 1);


  constructor(private router: Router, private formBuilder: FormBuilder) { }

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

}
