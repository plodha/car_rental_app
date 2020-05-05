import { Component, OnInit } from '@angular/core';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import * as moment from 'moment';

import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { NgxMatDatetimePickerModule, NgxMatTimepickerModule } from '@angular-material-components/datetime-picker';

//import { ApiService } from '../api.service';

import {ActivatedRoute, Router } from '@angular/router';
import {ApiService} from '../api.service'

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

  isLoadingResults = false;

  public pickupDateTime = new FormControl(new Date());
  public dropoffDateTime = new FormControl(new Date());


  vehicleType = new FormControl('');


  locations: Location[] = [];
   vehicleTypes: VehicleType[] = [];

   matcher = new MyErrorStateMatcher();


  constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,
      private api: ApiService) {
        this.actr.data.subscribe((data)=>{
          var locations = data.location;

          var i = 0;
          for (i = 0; i < locations.length; i++) {

            var obj = locations[i];

            var location = {
              id: obj.id,
              name: obj.name
            }
            this.locations[i] = location;
          }

        });


        this.actr.data.subscribe((data)=>{
          var vehicleTypes = data.vehicleType;
          console.log(vehicleTypes)
          var i = 0;
          for (i = 0; i < vehicleTypes.length; i++) {

            var obj = vehicleTypes[i];

            var vehicleType = {
              id: obj.id,
              value: obj.vehicleClass
            }
            this.vehicleTypes[i] = vehicleType;
          }

        });

      }

  ngOnInit(): void {
      this.reservationForm = this.formBuilder.group({

        // pickupDateTime: [null, Validators.required],
        // dropoffDateTime:[null, Validators.required],
        location: [null, Validators.required],
        vehicleType : [null, Validators.required],
        pickupDateTime: this.pickupDateTime,
        dropoffDateTime : this.dropoffDateTime,
        pickupdatestring: '',
        dropoffdatestring :''


      });

      //this.date = new Date(2021,9,4,5,6,7);

  }

  onFormSubmit() {
    this.isLoadingResults = true;
    if(this.validateFor72Hours()){
      this.router.navigate(['/login']);
    }
    //this.router.navigate(['/login']);
  }

  validateFor72Hours() {
    console.log(this.pickupDateTime.value);
    //Sun May 03 2020 17:56:13 GMT-0700 (Pacific Daylight Time)
    var pickuptime = this.reservationForm.get('pickupDateTime').value;
    var dropofftime = this.reservationForm.get('dropoffDateTime').value;

    var pickupdate = new Date(pickuptime);
    var pickupdatestring = ''+(pickupdate.getMonth()+1);
    pickupdatestring += "/"+pickupdate.getDate()+"/"+pickupdate.getFullYear()+" "+pickupdate.getHours()+":"+pickupdate.getMinutes();
    var dropoffdate = new Date(dropofftime);
    var dropoffdatestring = ''+(dropoffdate.getMonth()+1);
    dropoffdatestring += "/"+dropoffdate.getDate()+"/"+dropoffdate.getFullYear()+" "+dropoffdate.getHours()+":"+dropoffdate.getMinutes();
    console.log(pickupdatestring)
    console.log(dropoffdatestring)

    pickupdate = new Date(pickupdatestring)
    this.reservationForm.setValue({ pickupdatestring:pickupdatestring,dropoffdatestring:dropoffdatestring});
  //  this.reservationForm.set('dropoffdatestring').value = dropoffdatestring;

    var milliseconds = 72*60*60*1000;
    var a = new Date(dropoffdatestring)
    var b = new Date(pickupdatestring)
    let diff:any = +new Date(dropoffdatestring) -  +new Date(pickupdatestring);
    console.log(diff)
    console.log(diff > milliseconds)
    if (diff > milliseconds) {
      return false;
    }
  }


}
