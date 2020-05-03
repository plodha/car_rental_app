import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

interface Location {
  name: string;
  id: string;
}

interface VehicleType {
  value: string;
  id: string;
}

@Component({
  selector: 'app-add-vehicle-page',
  templateUrl: './add-vehicle-page.component.html',
  styleUrls: ['./add-vehicle-page.component.scss']
})
export class AddVehiclePageComponent implements OnInit {

  locations: Location[] = [
     {name: 'Sunnyvale', id: '1'},
     {name: 'San Jose', id: '2'},
     {name: 'Mountain View', id: '3'}
   ];
   vehicleTypes: VehicleType[] = [
      {value: 'Compact', id: '1'},
      {value: 'Luxury', id: '2'},
      {value: 'Van', id: '3'}
    ];

  addVehicleForm: FormGroup;
  make = '';
  model = '';
  year = '';
  VIN = '';
  licensePlate = '';
  location = '';
  vehicleType = '';

  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();


  constructor(private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.addVehicleForm = this.formBuilder.group({
      make : [null, [Validators.required,Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      model : [null, [Validators.required,Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      year : [null, [Validators.required, Validators.pattern("^[0-9]+$"),Validators.maxLength(7)]],
      VIN : [null, [Validators.required,Validators.pattern("^[A-Za-z0-9]+$")]],
      licensePlate : [null, [Validators.required, Validators.pattern("^[A-Za-z0-9]+$"), Validators.maxLength(7)]],
      location : [null, Validators.required],
      vehicleType : [null,  Validators.required],


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
