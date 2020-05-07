import { Component, OnInit, ViewChild } from '@angular/core';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import * as moment from 'moment';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';
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
interface Vehicle {
  vehicleId : number;
  locationId : number;
  vehicleSeatingCapacity: number;
  model : string;
  make : string;
  year: number;
  vehicleTypeId: number;
  estimatedPrice:number

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
  displayedColumns: string[] = ['id','model','estimatedPrice','star'];

  reservationForm : FormGroup
  location = new FormControl('');
  dataSource: MatTableDataSource<Vehicle>;
  isLoadingResults = false;
  estimatedPrice = 0;
  errorMessage = '';

  public pickupDateTime = new FormControl(new Date());
  public dropoffDateTime = new FormControl(new Date());

  ELEMENT_DATA: Vehicle[] = [];
  vehicleType = new FormControl('');
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

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
    console.log(localStorage.username != undefined)
if(localStorage.username != undefined ) {
  document.getElementById('profileName').innerHTML += localStorage.username
}
else {
  this.router.navigate(['/login']);

}
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
  closeWindow(){
    this.router.navigate(['/customerPage']);

  }

  onFormSubmit() {
    this.isLoadingResults = true;
    this.ELEMENT_DATA = [];
    this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);

    if(this.validateFor72Hours()){
      document.getElementById('errorBlock').style.display ='none';
    //locationId=2&vehicleTypeId=1&newPickUpTime=1/15/2020 10:00&newEstimatedDropOffTime=1/17/2020 10:00
    var formData1 = {}
    formData1['locationId'] = this.reservationForm.get('location').value;
    formData1['vehicleTypeId'] = this.reservationForm.get('vehicleType').value;
    formData1['pickupDateTime'] = this.getPickupDateString(this.reservationForm.get('pickupDateTime').value)
    formData1['dropoffDateTime'] =this.getPickupDateString(this.reservationForm.get('dropoffDateTime').value)

    console.log(formData1);
    var OBJ = this;

    this.api.getPriceInfo( this.reservationForm.get('vehicleType').value).subscribe((priceInfo:any)=>{
        console.log(priceInfo)
        console.log(priceInfo.length)
        if(priceInfo.length > 0){
          OBJ.estimatedPrice = priceInfo[0].hourlyPrice

    console.log('OBJ.estimatedPrice');
    console.log(OBJ.estimatedPrice)
    this.api.queryVehicleAvailability(formData1).subscribe((data:any)=>{
        console.log(data)
        var i = 0;

        for(i=0;i<data.length;i++)
        {
          console.log('estimatedPrice')
          console.log(OBJ.estimatedPrice);
          var obj = data[i]
          var vehicle = {
            vehicleId : obj.id,
            locationId : obj.location.id,
            vehicleSeatingCapacity: obj.vehicleTypeId.vehicleSize,
            model : obj.model,
            make : obj.make,
            year: obj.year,
            vehicleTypeId: obj.vehicleTypeId.id,
            estimatedPrice : OBJ.estimatedPrice
          }
        this.ELEMENT_DATA[i] = vehicle;
      }
      this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);

      this.isLoadingResults = false;
    });
  }
});
    //
    // this.dataSource.paginator = this.paginator;
    // this.dataSource.sort = this.sort;

    //  this.router.navigate(['/login']);
    }
    else{
      this.isLoadingResults = false;
      document.getElementById('errorBlock').style.display ='block';
      document.getElementById('errorBlock').innerHTML = this.errorMessage;
    }
    //this.router.navigate(['/login']);
  }
  getPickupDateString(value){
    var pickuptime = value;
    var pickupdate = new Date(pickuptime);
    var pickupdatestring = ''+(pickupdate.getMonth()+1);
    var min = pickupdate.getMinutes();
    var minStr = pickupdate.getMinutes().toString()
    if(min <10){
      minStr = '0'+ pickupdate.getMinutes().toString()
    }
    pickupdatestring += "/"+pickupdate.getDate()+"/"+pickupdate.getFullYear()+" "+pickupdate.getHours()+":"+minStr;
    return pickupdatestring;
  }

computeDiffHours(){
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
//  this.reservationForm.setValue({ pickupdatestring:pickupdatestring,dropoffdatestring:dropoffdatestring});
//  this.reservationForm.set('dropoffdatestring').value = dropoffdatestring;

  var milliseconds = 72*60*60*1000;
  var a = new Date(dropoffdatestring)
  var b = new Date(pickupdatestring)
  let diff:any = +new Date(dropoffdatestring) -  +new Date(pickupdatestring);
  diff = diff/(1000*60*60)
  console.log(diff)
  return diff;

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
  //  this.reservationForm.setValue({ pickupdatestring:pickupdatestring,dropoffdatestring:dropoffdatestring});
  //  this.reservationForm.set('dropoffdatestring').value = dropoffdatestring;





    var milliseconds = 72*60*60*1000;
    var onehour = 1*60*60*1000;
    var a = new Date(dropoffdatestring)
    var b = new Date(pickupdatestring)
    let diff:any = +new Date(dropoffdatestring) -  +new Date(pickupdatestring);
    console.log(diff)
    console.log(diff > milliseconds)
    if(diff < onehour){
      this.errorMessage = '*Please choose minimum one hour time range'
      return false;
    }

    if (diff > milliseconds) {
      this.errorMessage = '*Please choose dropoff time within 72 hours'
      return false;
    }
    if(a < new Date() || b < new Date()){
      this.errorMessage = '*Please choose some future date'
      return false;
    }
    return true;
  }
bookVehicle(ele){
  console.log(ele)

  var formData = {}
  var location = {}
  var invoice = {}
  var vehicle = {}
  vehicle['id'] = ele.vehicleId;
  invoice['id'] = 0
  location['id'] = ele.locationId;
  var OBJ = this;

  formData['pickUpTime'] = this.getPickupDateString(this.reservationForm.get('pickupDateTime').value)
  formData['actualDropOffTime'] =this.getPickupDateString(this.reservationForm.get('dropoffDateTime').value)
  formData['estimateDropOffTime'] = this.getPickupDateString(this.reservationForm.get('dropoffDateTime').value)
  console.log(localStorage.id);

  console.log(localStorage.username != undefined)
  if(localStorage.customerId != undefined ) {
    //document.getElementById('profileName').innerHTML += localStorage.username


      var customer = {}
      customer['id'] = localStorage.customerId;
      formData['customer'] = customer;
      formData['invoice'] = invoice;
      formData['location'] = location;
      formData['vehicle'] = vehicle;
      formData['estimatedPrice'] = ele.estimatedPrice
      formData['status'] = true
      console.log(formData)
      this.api.addReservationAPI(formData).subscribe((res:any) =>{
          console.log(res)
          if(res.status == "200") {
              alert("Reservation booked Successfully ! Thank you for your business")
                this.router.navigate(['/reservations/'+localStorage.customerId]);
          }
      });

}
else {
  this.router.navigate(['/login']);

}




}

}
