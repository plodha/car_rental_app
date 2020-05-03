import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import * as moment from 'moment';

import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { NgxMatDatetimePickerModule, NgxMatTimepickerModule } from '@angular-material-components/datetime-picker';

interface Location {
  name: string;
  id: string;
}
interface VehicleType {
  value: string;
  id: string;
}
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-add-reservation-page',
  templateUrl: './add-reservation-page.component.html',
  styleUrls: ['./add-reservation-page.component.scss']
})
export class AddReservationPageComponent implements OnInit {
//  @ViewChild('picker') picker: any;
  public date: moment.Moment;

  reservationForm : FormGroup
  location = new FormControl('');
  pickupDateTime = '';
  dropoffDateTime = '';
  isLoadingResults = false;


  vehicleType = new FormControl('');


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

   matcher = new MyErrorStateMatcher();


  constructor(private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
      this.reservationForm = this.formBuilder.group({

        // pickupDateTime: [null, Validators.required],
        // dropoffDateTime:[null, Validators.required],
        location: this.location,
        vehicleType : this.vehicleType,
        pickupDateTime: this.pickupDateTime,
        dropoffDateTime : this.dropoffDateTime


      });

      //this.date = new Date(2021,9,4,5,6,7);

  }

  onFormSubmit() {
    this.isLoadingResults = true;
    this.validateFor72Hours()
    this.router.navigate(['/login']);
  }

  validateFor72Hours() {

  }


}
