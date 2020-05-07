import { Component, OnInit } from '@angular/core';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

import {ActivatedRoute, Router } from '@angular/router';
import {ApiService} from '../api.service'
import * as _moment from 'moment';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

interface Location {
  name: string;
  id: number;
  address:number;
}

interface VehicleType {
  value: string;
  id: string;
}

@Component({
  selector: 'app-edit-vehicle-page',
  templateUrl: './edit-vehicle-page.component.html',
  styleUrls: ['./edit-vehicle-page.component.scss']
})
export class EditVehiclePageComponent implements OnInit {

  locations: Location[] = [];
   vehicleTypes: VehicleType[] = [];

  editVehicleForm: FormGroup;
  make = '';
  model = '';
  year = '';
  VIN = '';
  licensePlate = '';
  location = '';
  vehicleType = '';
  vehicleCondition = '';
  _id = ''
  status = ''
  serviceDate='';
  currentMileage='';
  registrationTag = '';


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
              name: obj.name,
              address: obj.address.id
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


    this.editVehicleForm = this.formBuilder.group({
      id :[null,Validators.required],
        address_id :[null,Validators.required],
      make : [null, [Validators.required,Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      model : [null, [Validators.required,Validators.pattern("^[A-Za-z0-9 ]+$")]],
      year : [null, [Validators.required, Validators.pattern("^[0-9]+$"),Validators.maxLength(7)]],
      VIN : [null, [Validators.required,Validators.pattern("^[A-Za-z0-9]+$")]],
      licensePlate : [null, [Validators.required, Validators.pattern("^[A-Za-z0-9]+$"), Validators.maxLength(7)]],
      location : [null, Validators.required],
      vehicleType : [null,  Validators.required],
      vehicleCondition:[null, [Validators.required,Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      status:[null, Validators.required],
      serviceDate:[null,Validators.required],
      currentMileage:[null,[Validators.required,Validators.pattern("^[0-9]+$")]],
      registrationTag:[null,[Validators.required,Validators.pattern("^[A-Za-z0-9 ]+$")]]
    });

    this.getVehicleById(this.actr.snapshot.params.id);



  }
  getVehicleById(id: any) {
    var object = this;
   this.api.getVehicleById(id).subscribe((data: any) => {

     console.log(data)
     console.log(data.serviceDate)
     this.editVehicleForm.setValue({
       id:data.id,
       address_id:data.location.address.id,
       make: data.make,
       model: data.model,
       year: data.year,
       VIN: data.vin,
       licensePlate: data.licensePlate,
       vehicleType: data.vehicleTypeId.id,
       location: data.location.id,
       vehicleCondition: data.vehicleCondition,
       status: data.status,
       serviceDate : new FormControl(new Date(data.serviceDate+ ' 09:00')),//data.serviceDate,
       currentMileage : data.currentMileage,
       registrationTag : data.registrationTag



     });
   });
 }

  onFormSubmit() {
    this.isLoadingResults = true;
    //this.router.navigate(['/login']);
    var location = {};
    var vehicleTypeId ={};
    var formData = {}
    var address = {};
    formData['id'] = this.editVehicleForm.get('id').value
    formData['make'] = this.editVehicleForm.get('make').value
    formData['model'] = this.editVehicleForm.get('model').value
    formData['year'] = this.editVehicleForm.get('year').value
    formData['vin'] = this.editVehicleForm.get('VIN').value
    formData['licensePlate'] = this.editVehicleForm.get('licensePlate').value
    vehicleTypeId['id'] = this.editVehicleForm.get('vehicleType').value
    formData['vehicleTypeId'] = vehicleTypeId
    location['id'] = this.editVehicleForm.get('location').value
    address['id'] = this.getAddressId(this.editVehicleForm.get('location').value)
    location['address'] = address;
    formData['location'] = location
    formData['vehicleCondition'] = this.editVehicleForm.get('vehicleCondition').value
    formData['status'] =  this.editVehicleForm.get('status').value

    formData['registrationTag'] = this.editVehicleForm.get('registrationTag').value;
    formData['currentMileage'] = this.editVehicleForm.get('currentMileage').value;
    formData['serviceDate'] = this.getPickupDateString(this.editVehicleForm.get('serviceDate').value);

    var id = this.editVehicleForm.get('id').value;
    console.log(id)
    this.api.updateVehicleAPI(id,formData).subscribe((res:any) => {
      console.log(res);
        this.isLoadingResults = false;
        this.router.navigate(['/vehicle']);
    }, (err: any) => {
      console.log(err);
      this.isLoadingResults = false;
        this.router.navigate(['/vehicle']);
    });


  }
  getAddressId(locationId) {
    console.log(locationId)
    var i =0;
    for(i=0;i<this.locations.length;i++){
      var loc = this.locations[i];
      if(loc.id == locationId){
        console.log(loc.address)
        return loc.address;
      }
    }
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
  closeWindow(){
      this.router.navigate(['/vehicle']);
  }
}
