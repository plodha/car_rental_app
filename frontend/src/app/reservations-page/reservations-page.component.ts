import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import {ActivatedRoute, Router ,NavigationEnd} from '@angular/router';
import {ApiService} from '../api.service'
import {Observable} from 'rxjs/Rx';
import { switchMap } from 'rxjs/operators';


export interface Reservation {
  id:number;
  actualDropOffTime:string;
  estimatedDropOffTime: string;
   estimatedPrice:number;
   pickUpTime:string;
   status: boolean;
   locationId:number;
   locationName:string;
    vehicleId:number;
    invoiceId : number;
    action: string;
    vehicleMake :string;
    vehicleModel:string;
    vehicleTypeId:number;



}
// const ELEMENT_DATA: Reservation[] = [
//   {id: 1, actualDropOffTime:'5/5/2020 19:55',estimatedDropOffTime: '5/5/2020 19:55', estimatedPrice:50, pickUpTime:'5/5/2020 19:55', status:true,locationId:1,locationName:'Test1',vehicleId:1,invoiceId:4,action:true,vehicleMake:'Audi',vehicleModel:'W2'},
//   {id: 1, actualDropOffTime:'5/5/2020 19:55',estimatedDropOffTime: '5/5/2020 19:55', estimatedPrice:50, pickUpTime:'5/5/2020 19:55', status:true,locationId:1,locationName:'Test1',vehicleId:1,invoiceId:4,action:true,vehicleMake:'Audi',vehicleModel:'W2'},
//   {id: 1, actualDropOffTime:'5/5/2020 19:55',estimatedDropOffTime: '5/5/2020 19:55', estimatedPrice:50, pickUpTime:'5/5/2020 19:55', status:true,locationId:1,locationName:'Test1',vehicleId:1,invoiceId:4,action:true,vehicleMake:'Audi',vehicleModel:'W2'},
//
// ];
@Component({
  selector: 'app-reservations-page',
  templateUrl: './reservations-page.component.html',
  styleUrls: ['./reservations-page.component.scss']
})
export class ReservationsPageComponent implements OnInit {
  displayedColumns: string[] = ['id', 'vehicleModel', 'pickUpTime','actualDropOffTime','estimatedDropOffTime','estimatedPrice','locationName','action'];
  dataSource: MatTableDataSource<Reservation>;
  ELEMENT_DATA: Reservation[] = [];


  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,private api: ApiService) {
    var OBJ = this;
    this.actr.data.subscribe((data) => {
       console.log(data);
       var reservations = data.reservation;

       var i = 0;
       for (i = 0; i < reservations.length; i++) {

         var obj = reservations[i];
         console.log(obj)
         var reservation = {
           id: obj.id,
           actualDropOffTime:obj.actualDropOffTime,
           estimatedDropOffTime: obj.estimateDropOffTime,
            estimatedPrice:obj.estimatedPrice,
            pickUpTime:obj.pickUpTime,
            status:obj.status,
            locationId:obj.location.id,
            locationName:obj.location.name,
             vehicleId:obj.vehicle.id,
             invoiceId : obj.invoice.id,
             action:OBJ.findAction(obj.pickUpTime,obj.estimateDropOffTime,obj.status),
             vehicleModel:obj.vehicle.model,
             vehicleMake:obj.vehicle.make,
             vehicleTypeId:obj.vehicle.vehicleTypeId.id
             //status : 'Active'
         }
         this.ELEMENT_DATA[i] = reservation;
       }


     });

     console.log(this.ELEMENT_DATA)
     this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);

  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    console.log(localStorage.username != undefined)
  if(localStorage.username != undefined ) {
    document.getElementById('profileName').innerHTML += localStorage.username
  }
  else {
    this.router.navigate(['/login']);

  }
  }


findAction(value,value2,status): string {
  var date = new Date(value)
    var dropoffdate = new Date(value2)

    var action = '0'
    console.log(new Date())
    console.log(new Date() < date)
    console.log(dropoffdate)
    console.log(new Date() > dropoffdate)
  if(new Date() < date && status){
    action = '1'; //cancel

  }
  if(new Date() > date && new Date() < dropoffdate && status){
  action = '2'; //dropoff

  }
  if(new Date() > date && new Date() > dropoffdate && status){
    action =  '3'; //invoice

  }
  if(!status){
    action =  '4'; //invoice

  }

  return action

}
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
viewInvoice(ele) {
  this.router.navigate(['/invoice/'+ele.invoiceId]);
}

initiateDropoff(ele) {
  this.router.navigate(['/survey/'+ele.id+'/'+ele.vehicleTypeId]);
}
initiateCancel(ele) {
  var formData = {}
  formData['reservationId'] = ele.id;
  var Onehr = 1000*60*60; // in milliseconds
  console.log(ele.pickUpTime)
  var d:any = new Date()
  var pickupDate:any = new Date(ele.pickUpTime);
  var diff:any =  d - pickupDate;
  console.log(diff)
  if (Math.abs(diff) > Onehr ){
    formData['isLatefee'] = false

  }
  else {
    formData['isLatefee'] = true
  }
  console.log(formData)
  this.api.cancelReservationAPI(formData).subscribe((res:any) => {
    console.log(res);
    if(res.status == "200" ){
        alert("Reservation cancelled Successfully !")
        location.reload();
    }
  });
  //this.router.navigate(['/survey/'+ele.id+'/'+ele.vehicleTypeId]);


}
}
