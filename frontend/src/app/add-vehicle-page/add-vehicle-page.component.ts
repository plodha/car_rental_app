import { Component, OnInit } from '@angular/core';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

import {ActivatedRoute, Router } from '@angular/router';
import {ApiService} from '../api.service'

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

  locations: Location[] = [];
   vehicleTypes: VehicleType[] = [];

  addVehicleForm: FormGroup;
  make = '';
  model = '';
  year = '';
  VIN = '';
  licensePlate = '';
  location = '';
  vehicleType = '';
  vehicleCondition = 'New';


  isLoadingResults = false;
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

        //console.log(this.locations)

  }

  ngOnInit(): void {
    this.addVehicleForm = this.formBuilder.group({
      make : [null, [Validators.required,Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      model : [null, [Validators.required,Validators.pattern("^[A-Za-z0-9 ]+$")]],
      year : [null, [Validators.required, Validators.pattern("^[0-9]+$"),Validators.maxLength(4)]],
      VIN : [null, [Validators.required,Validators.pattern("^[A-Za-z0-9]+$")]],
      licensePlate : [null, [Validators.required, Validators.pattern("^[A-Za-z0-9]+$"), Validators.maxLength(7)]],
      location : [null, Validators.required],
      vehicleType : [null,  Validators.required],
      vehicleCapacity : [],
      serviceDate:[null,Validators.required],
      currentMileage:[null,[Validators.required,Validators.pattern("^[0-9]+$")]],
      registrationTag:[null,[Validators.required,Validators.pattern("^[A-Za-z0-9 ]+$")]]

    });
  }
  getPickupDateString(value){
    var pickuptime = value;
    var pickupdate = new Date(pickuptime);
    var pickupmonthstring = ''+(pickupdate.getMonth()+1);
    var pickupdateStr = ''+pickupdate.getDate();
    var pickupdatestring = '';
    if(pickupdate.getDate() < 10){
      pickupdateStr = '0'+pickupdate.getDate();
    }
    pickupdatestring = pickupdate.getFullYear()+'-'+pickupmonthstring+'-'+pickupdateStr

    return pickupdatestring;
  }
  onFormSubmit() {
    this.isLoadingResults = true;
    //this.router.navigate(['/login']);
    var formData = {}
    formData['make'] = this.addVehicleForm.get('make').value
    formData['model'] = this.addVehicleForm.get('model').value
    formData['year'] = this.addVehicleForm.get('year').value
    formData['vIN'] = this.addVehicleForm.get('VIN').value
    formData['licensePlate'] = this.addVehicleForm.get('licensePlate').value
    formData['vehicleTypeId'] = this.addVehicleForm.get('vehicleType').value
    formData['locationId'] = this.addVehicleForm.get('location').value
    formData['vehicleCondition'] = 'new'
    formData['status'] = 1
    formData['registrationTag'] = this.addVehicleForm.get('registrationTag').value;
    formData['currentMileage'] = this.addVehicleForm.get('currentMileage').value;
    formData['serviceDate'] = this.getPickupDateString(this.addVehicleForm.get('serviceDate').value);

    this.api.addVehicleAPI(formData).subscribe((res:any) => {
      console.log(res);
        this.isLoadingResults = false;
        this.router.navigate(['/vehicle']);
    }, (err: any) => {
      console.log(err);
      this.isLoadingResults = false;
    });


  }
}
